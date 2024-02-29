package com.rte.controller;

import com.rte.model.entity.Product;
import com.rte.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/product-list")
    public String productList(Model model,
                              @RequestParam(name = "search", required = false) String search,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "20") int size,
                              @RequestParam(name = "sort", defaultValue = "exportPrice") String sort,
                              @RequestParam(name = "order", defaultValue = "asc") String order){
        List<Product> productList;
        int totalProducts = productService.countAllProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / size);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);

        if (search != null && !search.isEmpty()) {
            productList = productService.findByProductName(search);
        } else {
            if (!sort.isEmpty()) {
                productList = productService.findAllPageSortedByExportPrice(page, size, order);
            } else {
                productList = productService.findAllPage(page, size);
            }
        }
        model.addAttribute("productList", productList);
        return "product-list";
    }
}
