package com.hola.holalandweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorksController {

    @GetMapping("/works")
    public String goToWorks(Model model) {
        model.addAttribute("page", 1);
        return "works";
    }

    @GetMapping("/job-detail")
    public String getJobDetail(Model model) {
        model.addAttribute("page", 4);
        return "works";
    }
}
