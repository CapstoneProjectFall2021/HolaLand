package com.hola.holalandweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FptUniversityController {

    @GetMapping("/fpt-university")
    public String goToFptUniversity(Model model) {
        model.addAttribute("page", 1);
        return "fpt-university";
    }
}
