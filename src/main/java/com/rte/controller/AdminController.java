package com.rte.controller;

import com.rte.dto.request.ReqCategoryDTO;
import com.rte.dto.request.ReqProductDTO;
import com.rte.model.entity.*;
import com.rte.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.PasswordAuthentication;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailSubService emailSubService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;

    // Dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session) {
        int registeredAccounts = userService.countRegisteredAccounts();
        model.addAttribute("registeredAccounts", registeredAccounts);
        int totalProducts = productService.countAllProducts();
        model.addAttribute("totalProducts", totalProducts);
        int totalCategories = categoryService.countAllCategories();
        model.addAttribute("totalCategories", totalCategories);
        List<EmailSub> emailSubList = emailSubService.findAll();
        model.addAttribute("emailSubList", emailSubList);
        List<Contact> contactList = contactService.findAll();
        model.addAttribute("contactList", contactList);

        User userLogin = (User) session.getAttribute("loggedInUser");
        Cart cart = cartService.findByIdUser(userLogin.getUserId());

        if (cart != null) {
            List<CartItem> cartItems = cartItemService.findAllByIdCart(cart.getCart_id());

            double totalRevenue = cartItems.stream().mapToDouble(CartItem::getTotalRevenue).sum();
            model.addAttribute("totalRevenue", totalRevenue);

        }
        return "/admin/dashboard";
    }


    // Product
    @GetMapping("/product-manager")
    public String productManager(Model model,@RequestParam(name = "search", required = false) String search,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        List<Product> productList;
        int totalProducts = productService.countAllProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / size);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);

        if (search != null && !search.isEmpty()) {
            productList = productService.findByProductName(search);
        } else {
            productList = productService.findAllPage(page, size);
        }
        model.addAttribute("productList", productList);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "/admin/product-manager";
    }
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") @Valid ReqProductDTO productDTO,
                             BindingResult bindingResult,
                             @RequestParam("multipartFiles") List<MultipartFile> multipartFiles, Model model) {
        if (bindingResult.hasErrors()) {
            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("categoryList", categoryList);
            return "admin/add-product";
        }

        productService.createPro(productDTO, multipartFiles);
        return "redirect:/admin/product-manager";
    }
    @GetMapping("/add-product")
    public String addProduct(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product", new Product());
        return "/admin/add-product";
    }
    @GetMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
        productService.delete(productId);
        return "redirect:/admin/product-manager";
    }
    @GetMapping("/edit-product/{productId}")
    public String editProduct(@PathVariable Integer productId, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        Product product = productService.findId(productId);
        model.addAttribute("product", product);
        return "admin/edit-product";
    }
    @PostMapping("/edit-product")
    public String updateProduct(@ModelAttribute ReqProductDTO productDTO,
                                @RequestParam("multipartFiles") List<MultipartFile> multipartFiles, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        productService.updatePro(productDTO, multipartFiles);

        return "redirect:/admin/product-manager";
    }


    // User
    @GetMapping("/user-manager")
    public String userManager(Model model,
                              @RequestParam(name = "search", required = false) String search,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        List<User> userList;
        int totalUsers = userService.countRegisteredAccounts();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);

        if (search != null && !search.isEmpty()) {
            userList = userService.findByUserNameContaining(search);
        } else {
            userList = userService.findAllPage(page, size);
        }
        model.addAttribute("userList", userList);
        return "/admin/user-manager";
    }


    // Category
    @GetMapping("/category-manager")
    public String categoryManager(Model model,
                                  @RequestParam(name = "search", required = false) String search,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        List<Category> categoryList;
        int totalCategories = categoryService.countAllCategories();
        int totalPages = (int) Math.ceil((double) totalCategories / size);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);

        if (search != null && !search.isEmpty()) {
            categoryList = categoryService.findByCategoryName(search);
        } else {
            categoryList = categoryService.findAllPage(page, size);
        }
        model.addAttribute("categoryList", categoryList);
        return "/admin/category-manager";
    }
    @GetMapping("/add-category")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/add-category";
    }
    @PostMapping("/add-category")
    public String saveCategory(@ModelAttribute("category") @Valid ReqCategoryDTO categoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add-category";
        }
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setStatus(categoryDTO.getStatus());

        categoryService.create(category);
        return "redirect:/admin/category-manager";
    }
    @GetMapping("/delete-category/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/admin/category-manager";
    }

    @GetMapping("/edit-category/{categoryId}")
    public String editCategory(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.findId(categoryId);
        model.addAttribute("category", category);
        return "admin/edit-category";
    }
    @PostMapping("/edit-category")
    public String updateCategory(@ModelAttribute Category category) {
        categoryService.update(category, category.getCategoryId());
        return "redirect:/admin/category-manager";
    }


    // Order
    @GetMapping("/order-manager")
    public String orderManager() {
        return "/admin/order-manager";
    }



    // Profile
    @GetMapping("/profile")
    public String profile(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);

        if (redirectAttributes.containsAttribute("success")) {
            model.addAttribute("success", redirectAttributes.getFlashAttributes().get("success"));
        }
        return "/admin/profile";
    }
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") User user, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null && user.getUserId().equals(loggedInUser.getUserId())) {
            user.setEmail(loggedInUser.getEmail());
            userService.update(user, user.getUserId());
            session.setAttribute("loggedInUser", user);
        }
        return "redirect:/admin/profile";
    }
    @GetMapping("/change-password")
    public String changePassword() {
        return "/admin/change-password";
    }
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmNewPassword") String confirmNewPassword,
                                 HttpSession session, Model model, RedirectAttributes redirectAttributes) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (!passwordEncoder.matches(currentPassword, loggedInUser.getPassword())) {
            model.addAttribute("error", "Current password is incorrect");
            return "/admin/change-password";
        }

        if (!newPassword.equals(confirmNewPassword)) {
            model.addAttribute("error1", "New password and confirm new password do not match");
            return "/admin/change-password";
        }

        loggedInUser.setPassword(passwordEncoder.encode(newPassword));
        userService.update(loggedInUser, loggedInUser.getUserId());

        redirectAttributes.addFlashAttribute("success", "Password changed successfully");
        return "redirect:/admin/profile";
    }
    @GetMapping("/block-user/{userId}")
    public String blockUser(@PathVariable Integer userId) {
        userService.blockUser(userId);
        return "redirect:/admin/user-manager";
    }

    @GetMapping("/unblock-user/{userId}")
    public String unblockUser(@PathVariable Integer userId) {
        System.out.println(userId);
        userService.unblockUser(userId);
        return "redirect:/admin/user-manager";
    }
}
