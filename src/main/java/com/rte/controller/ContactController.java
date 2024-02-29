package com.rte.controller;

import com.rte.dto.request.ReqContactDTO;
import com.rte.model.entity.Contact;
import com.rte.model.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;
    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("reqContactDTO", new ReqContactDTO());
        return "contact";
    }
    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute @Valid ReqContactDTO reqContactDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }
        Contact contact = new Contact();
        contact.setName(reqContactDTO.getName());
        contact.setEmail(reqContactDTO.getEmail());
        contact.setOrderId(reqContactDTO.getOrderId());
        contact.setMessage(reqContactDTO.getMessage());

        if (contactService.create(contact)) {
            return "redirect:/thanks-contact";
        } else {
            return "error";
        }
    }
}
