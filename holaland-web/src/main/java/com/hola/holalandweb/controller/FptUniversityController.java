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
            return "404";
        }
        model.addAttribute("club", club);
        setClubModel(model, clubTypeId, clubTypeList, clubList, page);
        return "module-fpt-university";
    }

    @GetMapping("/fpt-university/department")
    public String goToFptUniversityDepartment(Model model) {
        model.addAttribute("page", 3);
        return "module-fpt-university";
    }

    @GetMapping("/fpt-university/lecturers")
    public String goToFptUniversityLecturers(Model model) {
        model.addAttribute("page", 4);
        return "module-fpt-university";
    }

    private void setClubModel(Model model, int clubTypeId, List<ClubType> clubTypeList, List<Club> clubList, int page) {
        model.addAttribute("clubTypeId", clubTypeId);
        model.addAttribute("clubTypeList", clubTypeList);
        model.addAttribute("clubList", clubList);
        model.addAttribute("page", page);
    }
}
