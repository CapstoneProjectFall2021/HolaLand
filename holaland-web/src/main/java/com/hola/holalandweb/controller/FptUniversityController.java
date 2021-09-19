package com.hola.holalandweb.controller;

import com.hola.holalandfptu.entity.Club;
import com.hola.holalandfptu.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FptUniversityController {

    private final ClubService clubService;

    @Autowired
    public FptUniversityController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/fpt-university")
    public String goToFptUniversity(Model model) {
        model.addAttribute("page", 1);
        return "fpt-university";
    }

    @GetMapping("/fpt-university-club")
    public String goToFptUniversityClub(Model model) {
        List<Club> clubList = clubService.getAllByType(1);
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
