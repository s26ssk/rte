package com.rte.controller;

import com.rte.model.entity.Cart;
import com.rte.model.entity.CartItem;
import com.rte.model.entity.Product;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductDetailController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable Integer id , Model model, RedirectAttributes redirectAttributes){
        Product product = productService.findId(id);
        model.addAttribute("proDetail", product);
        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getFlashAttributes().get("successMessage"));
        }
        return "product-detail";
    }

    @PostMapping("/product-detail")
    public String addToCart(@RequestParam("productId") int id,
                            @RequestParam("quantity") int quantity,
                            HttpSession session,
                            RedirectAttributes redirectAttributes ) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/user/login";
        }

        Cart cart = cartService.findByIdUser(user.getUserId());

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartService.addCart(cart);
        }

        cart = cartService.findByIdUser(user.getUserId());

        Product product = productService.findId(id);
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        if (cartItemService.create(cartItem)) {
            session.setAttribute("cartQuantity", cartItemService.findAllByIdCart(cart.getCart_id()).size());
            redirectAttributes.addFlashAttribute("successMessage", "Item was added to cart");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding product to cart.");
        }

        return "redirect:/product-detail/" + id;
    }
}

