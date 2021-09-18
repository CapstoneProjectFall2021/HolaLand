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

    @GetMapping("/fpt-university-club")
    public String goToFptUniversityClub(Model model) {
        model.addAttribute("page", 2);
        return "fpt-university";
    }

    @GetMapping("/fpt-university-department")
    public String goToFptUniversityDepartment(Model model) {
        model.addAttribute("page", 3);
        return "fpt-university";
    }

    @GetMapping("/fpt-university-lecturers")
    public String goToFptUniversityLecturers(Model model) {
        model.addAttribute("page", 4);
        return "fpt-university";
    }
}
