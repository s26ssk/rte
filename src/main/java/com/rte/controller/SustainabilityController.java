package com.rte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SustainabilityController {
    @GetMapping("/sustainability")
    public String sustainability(){
        return "sustainability";
    }
}
