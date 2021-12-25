package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodTagService;
import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.service.WorkRequestFindJobService;
import com.hola.holalandwork.service.WorkRequestRecruitmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final FoodItemService foodItemService;
    private final FoodTagService foodTagService;
    private final WorkRequestRecruitmentService workRequestRecruitmentService;
    private final WorkRequestFindJobService workRequestFindJobService;

    private String textSearch = "";

    public SearchController(
            FoodItemService foodItemService,
            FoodTagService foodTagService,
            WorkRequestRecruitmentService workRequestRecruitmentService,
            WorkRequestFindJobService workRequestFindJobService
    )
    {
        this.foodItemService = foodItemService;
        this.foodTagService = foodTagService;
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workRequestFindJobService = workRequestFindJobService;
    }

    @GetMapping("")
    public String search(Model model, @RequestParam("textSearch") String textSearch) {
        this.textSearch = textSearch;
        addSearchWorkAttr(model);
        return "search";
    }

    @GetMapping("/work")
    public String searchWork(Model model) {
        addSearchWorkAttr(model);
        return "search";
    }

    public void addSearchWorkAttr(Model model) {
        List<WorkRequestRecruitment> requestRecruitmentListSearch = workRequestRecruitmentService.searchByTitle(
                textSearch, Constants.STT_WORK_CODE_APPROVED);
        List<WorkRequestFindJob> requestFindJobListSearch = workRequestFindJobService.searchByTitle(
                textSearch, Constants.STT_WORK_CODE_APPROVED);
        model.addAttribute("requestRecruitmentListSearch", requestRecruitmentListSearch);
        model.addAttribute("requestFindJobListSearch", requestFindJobListSearch);
        model.addAttribute("textSearch", textSearch);
        model.addAttribute("page", 1);
    }

    @GetMapping("/food")
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
