package com.rte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ThanksContactController {
    @GetMapping("/thanks-contact")
    public String thanks_contact(){
        return "thanks-contact";
    }
}
