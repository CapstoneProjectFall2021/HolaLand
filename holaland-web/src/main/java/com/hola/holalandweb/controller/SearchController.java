package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodTagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final FoodItemService foodItemService;
    private final FoodTagService foodTagService;

    private String textSearch = "";

    public SearchController(FoodItemService foodItemService, FoodTagService foodTagService) {
        this.foodItemService = foodItemService;
        this.foodTagService = foodTagService;
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("textSearch") String textSearch) {
        this.textSearch = textSearch;
        model.addAttribute("page", 1);
        return "search";
    }

    @GetMapping("/search/food")
    public String searchFood(Model model) {
        List<FoodItem> foodItemListSearch = foodItemService.search(textSearch);
        List<FoodTag> foodTagListSearch = foodTagService.search(textSearch);

        model.addAttribute("foodTagListSearch",foodTagListSearch);
        model.addAttribute("foodItemListSearch",foodItemListSearch);
        model.addAttribute("textSearch", textSearch);
        model.addAttribute("page", 2);
        return "search";
    }
}
