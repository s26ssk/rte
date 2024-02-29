package com.rte.controller;

import com.rte.model.entity.EmailSub;
import com.rte.model.service.EmailSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscribeController {
    @Autowired
    private EmailSubService emailSubService;
    @GetMapping("/subscribe")
    public String subscribe(){
        return "subscribe";
    }


    @PostMapping("/subscribe")
    public String subscribe(@RequestParam("email") String email, Model model) {
        if (emailSubService.isEmailSubscribed(email)) {
            model.addAttribute("error", "Email is already subscribed");
            return "subscribe";
        }

        EmailSub newEmailSub = new EmailSub();
        newEmailSub.setEmailSub(email);

        boolean success = emailSubService.create(newEmailSub);

        if (success) {
            return "redirect:/thanks";
        } else {
            model.addAttribute("error", "Error occurred while subscribing");
            return "error";
        }
    }
}
