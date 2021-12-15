package com.hola.holalandweb.module.food.controller;

import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodType;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodTagService;
import com.hola.holalandfood.service.FoodTypeService;
import com.hola.holalandweb.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {

    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodTypeService foodTypeService;
    private final FoodTagService foodTagService;

    @Autowired
    public FoodController(
            FoodStoreOnlineService foodStoreOnlineService,
            FoodTypeService foodTypeService,
            FoodTagService foodTagService
    ) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTypeService = foodTypeService;
        this.foodTagService = foodTagService;
    }

    @GetMapping("")
    public String goToFood(Model model) {
        List<FoodType> foodTypeList = foodTypeService.getAll();
        List<FoodStoreOnline> foodStoreOnlineList = foodStoreOnlineService.getAllByType(
                foodTypeList.get(0).getFoodTypeId(),
                Constants.STT_FOOD_CODE_PENDING_APPROVAL
        );
        model.addAttribute("foodTypeId", foodTypeList.get(0).getFoodTypeId());
        model.addAttribute("foodTypeList", foodTypeList);
        model.addAttribute("foodStoreOnlineList", foodStoreOnlineList);
        model.addAttribute("foodTagService", foodTagService);
        model.addAttribute("page", 1);
        return "module-food";
    }

    @GetMapping("/type")
    public String getFoodStoreByType(@RequestParam("typeId") Integer typeId, Model model) {
        List<FoodType> foodTypeList = foodTypeService.getAll();
        List<FoodStoreOnline> foodStoreOnlineList = foodStoreOnlineService.getAllByType(
                typeId,
                Constants.STT_FOOD_CODE_PENDING_APPROVAL
        );
        model.addAttribute("foodTypeId", typeId);
        model.addAttribute("foodTypeList", foodTypeList);
        model.addAttribute("foodStoreOnlineList", foodStoreOnlineList);
        model.addAttribute("foodTagService", foodTagService);
        model.addAttribute("page", 1);
        return "module-food";
    }

    @GetMapping("/offline-store")
    public String goToOfflineStore(Model model) {
        model.addAttribute("page", 2);
        return "module-food";
    }
}
