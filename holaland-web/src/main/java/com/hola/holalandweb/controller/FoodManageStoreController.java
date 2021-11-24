package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.User;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FoodManageStoreController {

    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodTagService foodTagService;
    private final FoodItemService foodItemService;

    private User user;

    @Autowired
    public FoodManageStoreController(FoodStoreOnlineService foodStoreOnlineService, FoodTagService foodTagService,
                                     FoodItemService foodItemService) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTagService = foodTagService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/store/info")
    public String getShopInfo(Model model) {
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByUserId(1);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("page", 1);
        return "module-food-manage-store";
    }

    @GetMapping("/store/manage-food")
    public String manageFood(Model model) {
        List<FoodItem> foodItemList = foodItemService.getAllByUserId(2);
        List<FoodTag> foodTagList = foodTagService.getAllByUserId(2);

        model.addAttribute("foodItemList", foodItemList);
        model.addAttribute("foodTagList", foodTagList);
        model.addAttribute("page", 2);
        return "module-food-manage-store";
    }

    @GetMapping("/store/manage-order")
    public String manageOrder(Model model) {
        model.addAttribute("page", 3);
        return "module-food-manage-store";
    }

    @GetMapping("/store/statistics")
    public String statistics(Model model) {
        model.addAttribute("page", 4);
        return "module-food-manage-store";
    }
}
