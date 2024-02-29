package com.rte.controller;

import com.rte.model.entity.ShippingPrice;
import com.rte.model.service.ShippingPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShippingController {
    @Autowired
    private ShippingPriceService shippingPriceService;
    @GetMapping("/shipping-price")
    public String shippingPrice(Model model){
        List<ShippingPrice> shippingPriceList = shippingPriceService.findAll();
        System.out.println(shippingPriceList);
        model.addAttribute("shippingPriceList", shippingPriceList);
        return "shipping-price";
    }
}
