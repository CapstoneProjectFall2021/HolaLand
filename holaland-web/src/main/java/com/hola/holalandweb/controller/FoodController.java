package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodStoreOnlineTag;
import com.hola.holalandfood.entity.FoodType;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodStoreOnlineTagService;
import com.hola.holalandfood.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FoodController {

    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodTypeService foodTypeService;
    private final FoodStoreOnlineTagService foodStoreOnlineTagService;

    @Autowired
    public FoodController(FoodStoreOnlineService foodStoreOnlineService,
                          FoodTypeService foodTypeService,
                          FoodStoreOnlineTagService foodStoreOnlineTagService) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTypeService = foodTypeService;
        this.foodStoreOnlineTagService = foodStoreOnlineTagService;
    }

    @GetMapping("/food")
    public String goToFood(Model model) {
        List<FoodType> foodTypeList = foodTypeService.getAll();
        List<FoodStoreOnline> foodStoreOnlineList = foodStoreOnlineService.getAllByType(
                foodTypeList.get(0).getFoodTypeId(),
                1);
        List<FoodStoreOnlineTag> foodStoreOnlineTagList = foodStoreOnlineTagService.getAll();
        model.addAttribute("typeId", foodTypeList.get(0).getFoodTypeId());
        model.addAttribute("foodTypeList",foodTypeList);
        model.addAttribute("foodStoreOnlineList", foodStoreOnlineList);
        model.addAttribute("foodStoreOnlineTagList", foodStoreOnlineTagList);
        model.addAttribute("page", 1);
        return "module-food";
    }

    @GetMapping("/food/type")
    public String getFoodsByType(
            @RequestParam("typeId") Integer typeId,
            Model model) {
        List<FoodType> foodTypeList = foodTypeService.getAll();
        List<FoodStoreOnline> foodStoreOnlineList = foodStoreOnlineService.getAllByType(
                typeId,
                1);
        List<FoodStoreOnlineTag> foodStoreOnlineTagList = foodStoreOnlineTagService.getAll();
        model.addAttribute("typeId", typeId);
        model.addAttribute("foodTypeList",foodTypeList);
        model.addAttribute("foodStoreOnlineList", foodStoreOnlineList);
        model.addAttribute("foodStoreOnlineTagList", foodStoreOnlineTagList);
        model.addAttribute("page", 1);
        return "module-food";
    }

    @GetMapping("/food/online-store")
    public String goToOnlineStore(Model model) {
        model.addAttribute("page", 9);
        return "module-food";
    }

    @GetMapping("/food/list-offline-store")
    public String goToOfflineStore(Model model) {
        model.addAttribute("page", 2);
        return "module-food";
    }

    @GetMapping("/food/user-order")
    public String goToUserOrder(Model model) {
        model.addAttribute("page", 3);
        return "module-food";
    }

    @GetMapping("/food/manage-store")
    public String goToManageStore(Model model) {
        model.addAttribute("page", 1);
        return "module-food-manage-store";
    }
}
