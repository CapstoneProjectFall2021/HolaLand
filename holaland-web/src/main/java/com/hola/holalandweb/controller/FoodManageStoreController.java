package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodStoreOnlineTag;
import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodStoreOnlineTagService;
import com.hola.holalandfood.service.FoodTagService;
import com.hola.holalandweb.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodManageStoreController {

    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodTagService foodTagService;
    private final FoodItemService foodItemService;
    private final FoodStoreOnlineTagService foodStoreOnlineTagService;

    @Autowired
    public FoodManageStoreController(
            FoodStoreOnlineService foodStoreOnlineService,
            FoodTagService foodTagService,
            FoodItemService foodItemService,
            FoodStoreOnlineTagService foodStoreOnlineTagService
    ) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTagService = foodTagService;
        this.foodItemService = foodItemService;
        this.foodStoreOnlineTagService = foodStoreOnlineTagService;
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
            @RequestParam("storeDescription") String storeDescription
    ) {
        FoodStoreOnline newShopInfo = FoodStoreOnline.builder().build();
        newShopInfo.setFoodStoreOnlineId(shopId);
        newShopInfo.setFoodStoreOnlineName(shopName);
        newShopInfo.setFoodStoreOnlineDescription(storeDescription);
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
        foodItem.setFoodItemDeleted(1);
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
        System.out.println(currentUser.getId());
        List<FoodItem> foodShopItemList = foodItemService.getAllByUserId(currentUser.getId());
        List<FoodTag> foodShopTagList = foodTagService.getAllByUserId(currentUser.getId());
        List<FoodTag> foodTagList = foodTagService.getAll();
        model.addAttribute("foodStoreItemList", foodShopItemList);
        model.addAttribute("foodStoreTagList", foodShopTagList);
        model.addAttribute("foodTagList", foodTagList);
        model.addAttribute("page", 2);
        return "module-food-manage-store";
    }

    @PostMapping("/store/manage-food/update-tag")
    public String updateFormUpdateTagShop(Model model, @RequestParam(value = "tagList") List<Integer> tagList, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }

        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByUserId(currentUser.getId());
        List<FoodStoreOnlineTag> foodStoreOnlineTags = new ArrayList<>();
        for (Integer foodTagId : tagList) {
            FoodStoreOnlineTag foodStoreOnlineTag = new FoodStoreOnlineTag();
            foodStoreOnlineTag.setFoodStoreOnlineId(foodStoreOnline.getFoodStoreOnlineId());
            foodStoreOnlineTag.setFoodTagId(foodTagId);
            foodStoreOnlineTags.add(foodStoreOnlineTag);
        }
        //delete all old
        foodStoreOnlineTagService.deleteAllTagByFoodStoreOnlineId(foodStoreOnline.getFoodStoreOnlineId());
        //insert new tag after update
        foodStoreOnlineTagService.insertTagForFoodStore(foodStoreOnlineTags);
        //get list tag after update
        List<FoodTag> foodShopTagList = foodTagService.getAllByUserId(currentUser.getId());
        List<FoodItem> foodShopItemList = foodItemService.getAllByUserId(currentUser.getId());
        model.addAttribute("foodStoreTagList", foodShopTagList);
        model.addAttribute("foodStoreItemList", foodShopItemList);
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

    @PostMapping("/food/save-image-food")
    public String saveImageFood(Model model, @RequestParam("imageFood") MultipartFile multipartFile,
                                @ModelAttribute("foodItem") FoodItem foodItem,
                                BindingResult bindingResult, Authentication authentication) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }

        int foodStoreOnlineId = foodStoreOnlineService.getOneByUserId(currentUser.getId()).getFoodStoreOnlineId();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        foodItem.setFoodItemImage(fileName);
        foodItem.setFoodStoreOnlineId(foodStoreOnlineId);
        foodItem.setFoodItemIsActive(1);
        foodItem.setFoodItemDeleted(0);
        String uploadDir = new File("holaland-web/src/main/resources/static/images/food").getAbsolutePath();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        foodItemService.save(foodItem);
        //get list tag after update

        List<FoodTag> foodShopTagList = foodTagService.getAllByUserId(currentUser.getId());
        List<FoodItem> foodShopItemList = foodItemService.getAllByUserId(currentUser.getId());
        model.addAttribute("foodStoreTagList", foodShopTagList);
        model.addAttribute("foodStoreItemList", foodShopItemList);
        model.addAttribute("page", 2);
        return "module-food-manage-store";
    }
}
