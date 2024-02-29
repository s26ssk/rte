package com.rte.controller;

import com.rte.dto.request.ReqUserLoginDTO;
import com.rte.dto.request.ReqUserRegisterDTO;
import com.rte.dto.response.RespUserLoginDTO;
import com.rte.model.entity.Cart;
import com.rte.model.entity.User;
import com.rte.model.service.CartItemService;
import com.rte.model.service.CartService;
import com.rte.model.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;
    @GetMapping("")
    public String home(){
        return "login";
    }


    @GetMapping("/login")
    public String login(Model model, @CookieValue(value = "userEmail", defaultValue = "") String userEmail, RedirectAttributes redirectAttributes) {
        model.addAttribute("user", new ReqUserLoginDTO());
        model.addAttribute("savedEmail", userEmail);
        if (redirectAttributes.containsAttribute("loginError")) {
            model.addAttribute("loginError", redirectAttributes.getFlashAttributes().get("loginError"));
        }
        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getFlashAttributes().get("successMessage"));
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @Valid @ModelAttribute("user") ReqUserLoginDTO userDTO,
            BindingResult bindingResult,
            Model model,
            HttpServletResponse response,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginError", "Incorrect email or password");
            return "login";
        }

        boolean loginSuccess = userService.loginUser(userDTO.getEmail(), userDTO.getPassword());

        if (loginSuccess) {
            User user = userService.findByEmail(userDTO.getEmail());
            session.setAttribute("loggedInUser", user);
            Cookie emailCookie = new Cookie("userEmail", userDTO.getEmail());
            emailCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(emailCookie);

            Cart cart = cartService.findByIdUser(user.getUserId());
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                cartService.addCart(cart);
            }
            session.setAttribute("cartQuantity", cartItemService.findAllByIdCart(cart.getCart_id()).size());

            if (user.getRole() == 1) {
                redirectAttributes.addFlashAttribute("loggedInAdmin", user);
                return "redirect:/admin/dashboard";
            }
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("loginError", "Incorrect email or password");
            model.addAttribute("loginError", "Incorrect email or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new ReqUserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") ReqUserRegisterDTO userDTO,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user = convertToUser(userDTO);
        boolean registrationSuccess = userService.registerUser(user);
        if (registrationSuccess) {
            redirectAttributes.addFlashAttribute("successMessage", "Registration success");
            return "redirect:/user/login";
        } else {
            model.addAttribute("registrationError", "Email is already in use");
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("user", new ReqUserLoginDTO());
        return "forgot-password";
    }

    private User convertToUser(ReqUserRegisterDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
    private RespUserLoginDTO convertToRespUserDTO(User user) {
        return new RespUserLoginDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getRole(),
                user.getUserStatus()
        );
    }
}
