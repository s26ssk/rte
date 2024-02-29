package com.rte.controller;

import com.rte.model.entity.Cart;
import com.rte.model.entity.CartItem;
import com.rte.model.entity.User;
import com.rte.model.service.CartItemService;
import com.rte.model.service.CartService;
import com.rte.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class CartController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session,RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            Cart cart = cartService.findByIdUser(user.getUserId());
            if (cart != null) {
                List<CartItem> cartItems = cartItemService.findAllByIdCart(cart.getCart_id());
                model.addAttribute("cartItems", cartItems);
                double total = 0;
                for (CartItem ca : cartItems) {
                    total += ca.getQuantity() * ca.getProduct().getExport_price();
                }
                model.addAttribute("total", total) ;
            }
        } else {
            return "redirect:/user/login";
        }
        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getFlashAttributes().get("successMessage"));
        }
        return "cart";
    }
    @GetMapping("/delete-cartItem/{id}")
    public String deleteCart(@PathVariable Integer id, HttpSession session) {
        cartItemService.delete(id);
        User user = (User) session.getAttribute("loggedInUser");
        Cart cart = cartService.findByIdUser(user.getUserId());
        session.setAttribute("cartQuantity", cartItemService.findAllByIdCart(cart.getCart_id()).size());
        return "redirect:/cart";
    }
    @PostMapping("/cart")
    public String checkout(Model model, HttpSession session , RedirectAttributes redirectAttributes) {
        User userLogin = (User) session.getAttribute("loggedInUser");
        Cart cart = cartService.findByIdUser(userLogin.getUserId());

        if (cart != null) {
            if (cartService.deleteCart(cart.getCart_id())) {
                Cart cart1 = cartService.findByIdUser(userLogin.getUserId());
                if (cart1 == null) {
                    cart1 = new Cart();
                    cart1.setUser(userLogin);
                    cartService.addCart(cart1);
                }
                session.setAttribute("cartQuantity", cartItemService.findAllByIdCart(cart1.getCart_id()).size());
                redirectAttributes.addFlashAttribute("successMessage", "Purchase successful!");
            } else {
                model.addAttribute("errorMessage", "error");
            }
        } else {
            model.addAttribute("errorMessage", "error");
        }

        return "redirect:/cart";
    }

}
