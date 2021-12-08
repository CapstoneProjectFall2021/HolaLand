package com.hola.holalandweb.module.food.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/store")
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

    @GetMapping("/info")
    public String getShopInfo(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByUserId(currentUser.getId());
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("page", 3);
        return "module-food-manage-store";
    }

    // update avatar image pending
    @PostMapping("/update")
    public String updatingShopInfo(
            @RequestParam("storeId") Integer shopId,
            @RequestParam("storeName") String shopName,
            @RequestParam("storeDescription") String storeDescription
    ) {
        FoodStoreOnline storeInfoUpdate = FoodStoreOnline.builder()
                .foodStoreOnlineId(shopId)
                .foodStoreOnlineName(shopName)
                .foodStoreOnlineDescription(storeDescription)
                .build();
        boolean isCheck = foodStoreOnlineService.updateShopInfo(storeInfoUpdate);
        if (isCheck) {
            return "redirect:" + "/store/info";
        } else {
            return "404";
        }
    }

    @GetMapping("/manage/food")
    public String manageFood(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

        List<FoodItem> foodShopItemList = foodItemService.getAllByUserId(currentUser.getId());
        List<FoodTag> foodShopTagList = foodTagService.getAllByUserId(currentUser.getId());

        model.addAttribute("foodStoreItemList", foodShopItemList);
        model.addAttribute("foodStoreTagList", foodShopTagList);
        model.addAttribute("page", 2);
        return "module-food-manage-store";
    }

    @GetMapping("/manage/food/delete")
    public String deleteFoodManagerStore(@RequestParam("foodId") Integer foodId) {
        FoodItem foodItem = FoodItem.builder().build();
        foodItem.setFoodItemId(foodId);
        foodItem.setFoodItemDeleted(1);
        boolean isCheck = foodItemService.deletedOne(foodItem);
        if (isCheck) {
            return "redirect:" + "/store/manage/food";
        } else {
            return "404";
        }
    }

    @GetMapping("/manage/tag")
    public String getFormUpdateStoreTag(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

        List<FoodItem> foodShopItemList = foodItemService.getAllByUserId(currentUser.getId());
        List<FoodTag> foodShopTagList = foodTagService.getAllByUserId(currentUser.getId());
        List<FoodTag> foodTagList = foodTagService.getAll();
        model.addAttribute("foodStoreItemList", foodShopItemList);
        model.addAttribute("foodStoreTagList", foodShopTagList);
        model.addAttribute("foodTagList", foodTagList);
        model.addAttribute("page", 2);
        return "module-food-manage-store";
    }

    @PostMapping("/manage/tag/update")
    public String updateStoreTag(
            Model model,
            @RequestParam(value = "tagList") List<Integer> tagList,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

        // Get current list tag
        List<FoodTag> foodStoreCurrentTagList = foodTagService.getAllByUserId(currentUser.getId());

        // Get list tag if remove
        // Check tag id in table food items


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

    // Not yet
    @GetMapping("/store/statistics")
    public String statistics(Model model) {
        model.addAttribute("page", 4);
        return "module-food-manage-store";
    }

    // Fix upload image
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
        //String uploadDir = new File("holaland-web/src/main/resources/static/images/food").getAbsolutePath();
        String uploadDir = new File("holaland-web/target/classes/static/images/food").getAbsolutePath();
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
