package com.hola.holalandweb.controller;

import com.hola.holalandfptu.entity.Club;
import com.hola.holalandfptu.entity.ClubType;
import com.hola.holalandfptu.service.ClubService;
import com.hola.holalandfptu.service.ClubTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FptUniversityController {

    private final ClubTypeService clubTypeService;
    private final ClubService clubService;

    @Autowired
    public FptUniversityController(ClubService clubService, ClubTypeService clubTypeService) {
        this.clubTypeService = clubTypeService;
        this.clubService = clubService;
    }

    @GetMapping("/fpt-university")
    public String goToFptUniversity(Model model) {
        model.addAttribute("page", 1);
        return "fpt-university";
    }

    @GetMapping("/fpt-university/club")
    public String goToFptUniversityClub(Model model) {
        List<ClubType> clubTypeList = clubTypeService.getAll();
        List<Club> clubList = clubService.getAllByType(clubTypeList.get(0).getFptuClubTypeId());
        model.addAttribute("clubTypeId", clubTypeList.get(0).getFptuClubTypeId());
        model.addAttribute("clubTypeList", clubTypeList);
        model.addAttribute("clubList", clubList);
        model.addAttribute("page", 2);
        return "fpt-university";
    }

    @GetMapping("/fpt-university/club/type")
    public String getFptUniversityClubType(
            @RequestParam("clubTypeId") Integer clubTypeId,
            @RequestParam("page") Integer page,
            Model model
    ) {
        List<ClubType> clubTypeList = clubTypeService.getAll();
        List<Club> clubList = clubService.getAllByType(clubTypeId);
        model.addAttribute("clubTypeId", clubTypeId);
        model.addAttribute("clubTypeList", clubTypeList);
        model.addAttribute("clubList", clubList);
        model.addAttribute("page", page);
        return "fpt-university";
    }

    @GetMapping("/fpt-university/club/detail")
    public String getFptUniversityClubDetail(
            @RequestParam("clubTypeId") Integer clubTypeId,
            @RequestParam("clubId") Integer clubId,
            @RequestParam("page") Integer page,
            Model model
    ) {
        List<ClubType> clubTypeList = clubTypeService.getAll();
        List<Club> clubList = clubService.getAllByType(clubTypeId);
        Club club;
        try {
            club = clubService.getOne(clubId);
        } catch (EmptyResultDataAccessException ex) {
            return "error";
        }
        model.addAttribute("clubTypeList", clubTypeList);
        model.addAttribute("clubList", clubList);
        model.addAttribute("club", club);
        model.addAttribute("page", page);
        return "fpt-university";
    }

    @GetMapping("/fpt-university/department")
    public String goToFptUniversityDepartment(Model model) {
        model.addAttribute("page", 3);
        return "fpt-university";
    }

    @GetMapping("/fpt-university/lecturers")
    public String goToFptUniversityLecturers(Model model) {
        model.addAttribute("page", 4);
        return "fpt-university";
    }
}
