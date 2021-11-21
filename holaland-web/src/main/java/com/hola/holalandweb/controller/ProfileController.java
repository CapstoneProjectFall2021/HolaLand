package com.hola.holalandweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("page", 1);

        System.out.println(passwordEncoder.encode("123456"));

        // check old password
        System.out.println(passwordEncoder.matches("123456", "$2a$10$S5XdhGZdPq5eqkI6DLez6ucT4JywAFhltqz8h7sMRT5xvh1kVkHFK"));
        return "profile";
    }

    @GetMapping("/profile-update")
    public String profileUpdate(Model model) {
        model.addAttribute("page", 3);
        return "profile";
    }
}
