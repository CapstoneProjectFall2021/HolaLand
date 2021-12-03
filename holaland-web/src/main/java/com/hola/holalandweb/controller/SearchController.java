package com.hola.holalandweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    private String textSearch = "";

    @GetMapping("")
    public String search(Model model, @RequestParam("textSearch") String textSearch) {
        this.textSearch = textSearch;
        model.addAttribute("page", 1);
        return "search";
    }

    @GetMapping("/food")
    public String searchFood(Model model) {
        model.addAttribute("page", 2);
        return "search";
    }
}
