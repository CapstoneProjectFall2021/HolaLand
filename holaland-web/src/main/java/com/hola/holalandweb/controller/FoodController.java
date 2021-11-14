package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.*;
import com.hola.holalandfood.service.*;
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
    private final FoodTagService foodTagService;
    private final FoodItemService foodItemService;
    private final FoodStoreOnlineRateService foodStoreOnlineRateService;

    @Autowired
    public FoodController(FoodStoreOnlineService foodStoreOnlineService,
                          FoodTypeService foodTypeService,
                          FoodStoreOnlineTagService foodStoreOnlineTagService,
                          FoodTagService foodTagService,
                          FoodItemService foodItemService,
                          FoodStoreOnlineRateService foodStoreOnlineRateService) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTypeService = foodTypeService;
        this.foodStoreOnlineTagService = foodStoreOnlineTagService;
        this.foodTagService = foodTagService;
        this.foodItemService = foodItemService;
        this.foodStoreOnlineRateService = foodStoreOnlineRateService;
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
    public String goToOnlineStore(
            @RequestParam("id") Integer id,
            Model model) {
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOne(id);
        List<FoodTag> foodStoreOnlineTagList = foodTagService.getAllByStoreOnlineId(id);
        List<FoodItem> foodItemList = foodItemService.getAllByStoreOnlineId(id);
        List<FoodStoreOnlineRate> listComment = foodStoreOnlineRateService.getAllCommentByStoreOnlineId(id);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("foodStoreOnlineTagList", foodStoreOnlineTagList);
        model.addAttribute("foodItemList", foodItemList);
        model.addAttribute("listComment", listComment);
        model.addAttribute("page", 9);
        return "module-food";
    }

    @GetMapping("/food/online-store/tag")
    public String getFoodOnlineStoreByTag(
            @RequestParam("tagId") Integer tagId,
            @RequestParam("id") Integer id,
            Model model) {
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOne(id);
        List<FoodTag> foodStoreOnlineTagList = foodTagService.getAllByStoreOnlineId(id);
        List<FoodStoreOnlineRate> listComment = foodStoreOnlineRateService.getAllCommentByStoreOnlineId(id);
        List<FoodItem> foodItemList = foodItemService.getAllByStoreOnlineIdAndTagId(id, tagId);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("foodStoreOnlineTagList", foodStoreOnlineTagList);
        model.addAttribute("foodItemList", foodItemList);
        model.addAttribute("listComment", listComment);
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
