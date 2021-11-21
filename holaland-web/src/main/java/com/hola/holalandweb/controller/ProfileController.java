package com.hola.holalandweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("page", 1);
        return "profile";
    }

    @GetMapping("/profile-update")
    public String profileUpdate(Model model) {
        model.addAttribute("page", 3);
        return "profile";
    }
}
