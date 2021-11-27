package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FoodManageStoreController {

    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodTagService foodTagService;
    private final FoodItemService foodItemService;

    @Autowired
    public FoodManageStoreController(
            FoodStoreOnlineService foodStoreOnlineService,
            FoodTagService foodTagService,
            FoodItemService foodItemService
    ) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTagService = foodTagService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/store/info")
    public String getShopInfo(Model model, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }

        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByUserId(currentUser.getId());
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("page", 1);
        return "module-food-manage-store";
    }

    @PostMapping("/update-store-info")
    public String updatingShopInfo(
            @RequestParam("storeId") Integer shopId,
            @RequestParam("storeName") String shopName,
            @RequestParam("storeDescript") String shopDescript
    ) {
        FoodStoreOnline newShopInfo = FoodStoreOnline.builder().build();
        newShopInfo.setFoodStoreOnlineId(shopId);
        newShopInfo.setFoodStoreOnlineName(shopName);
        newShopInfo.setFoodStoreOnlineDescription(shopDescript);
        boolean isCheck = foodStoreOnlineService.updateShopInfo(newShopInfo);
        if (isCheck) {
            return "redirect:" + "/store/info";
        } else {
            return "404";
        }
    }

    @GetMapping("/store/manage-food")
    public String manageFood(Model model, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        List<FoodItem> foodShopItemList = foodItemService.getAllByUserId(currentUser.getId());
        List<FoodTag> foodShopTagList = foodTagService.getAllByUserId(currentUser.getId());
        model.addAttribute("foodStoreItemList", foodShopItemList);
        model.addAttribute("foodStoreTagList", foodShopTagList);
        model.addAttribute("page", 2);
        return "module-food-manage-store";
    }

    @GetMapping("/store/manage-food/delete")
    public String deleteFoodManagerStore(@RequestParam("foodId") Integer foodId) {
        FoodItem foodItem = FoodItem.builder().build();
        foodItem.setFoodItemId(foodId);
        foodItem.setFoodItemDeleted(true);
        boolean isCheck = foodItemService.deletedOne(foodItem);
        if (isCheck) {
            return "redirect:" + "/store/manage-food";
        } else {
            return "404";
        }
    }

    @GetMapping("/store/manage-food/store-tag")
    public String getFormUpdateTagShop(Model model, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        List<FoodItem> foodShopItemList = foodItemService.getAllByUserId(currentUser.getId());
        List<FoodTag> foodShopTagList = foodTagService.getAllByUserId(currentUser.getId());
        List<FoodTag> foodTagList = foodTagService.getAll();
        model.addAttribute("foodStoreItemList", foodShopItemList);
        model.addAttribute("foodStoreTagList", foodShopTagList);
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
