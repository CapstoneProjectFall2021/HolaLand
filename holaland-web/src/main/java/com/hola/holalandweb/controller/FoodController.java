package com.hola.holalandweb.controller;

import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.entity.FoodType;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodStoreOnlineRateService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodStoreOnlineTagService;
import com.hola.holalandfood.service.FoodTagService;
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
    private final FoodTagService foodTagService;
    private final FoodItemService foodItemService;
    private final FoodStoreOnlineRateService foodStoreOnlineRateService;
    private final UserDetailService userDetailService;

    @Autowired
    public FoodController(FoodStoreOnlineService foodStoreOnlineService,
                          FoodTypeService foodTypeService,
                          FoodStoreOnlineTagService foodStoreOnlineTagService,
                          FoodTagService foodTagService,
                          FoodItemService foodItemService,
                          FoodStoreOnlineRateService foodStoreOnlineRateService,
                          UserDetailService userDetailService) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTypeService = foodTypeService;
        this.foodStoreOnlineTagService = foodStoreOnlineTagService;
        this.foodTagService = foodTagService;
        this.foodItemService = foodItemService;
        this.foodStoreOnlineRateService = foodStoreOnlineRateService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/food")
    public String goToFood(Model model) {
        List<FoodType> foodTypeList = foodTypeService.getAll();
        List<FoodStoreOnline> foodStoreOnlineList = foodStoreOnlineService.getAllByType(
                foodTypeList.get(0).getFoodTypeId(),
                1
        );
        model.addAttribute("foodTypeId", foodTypeList.get(0).getFoodTypeId());
        model.addAttribute("foodTypeList", foodTypeList);
        model.addAttribute("foodStoreOnlineList", foodStoreOnlineList);
        model.addAttribute("foodTagService", foodTagService);
        model.addAttribute("page", 1);
        return "module-food";
    }

    @GetMapping("/food/type")
    public String getFoodsByType(@RequestParam("typeId") Integer typeId, Model model) {
        List<FoodType> foodTypeList = foodTypeService.getAll();
        List<FoodStoreOnline> foodStoreOnlineList = foodStoreOnlineService.getAllByType(
                typeId,
                1
        );
        model.addAttribute("foodTypeId", typeId);
        model.addAttribute("foodTypeList", foodTypeList);
        model.addAttribute("foodStoreOnlineList", foodStoreOnlineList);
        model.addAttribute("foodTagService", foodTagService);
        model.addAttribute("page", 1);
        return "module-food";
    }


    @GetMapping("/food/online-store")
    public String goToOnlineStore(@RequestParam("id") Integer id, Model model) {
        addAttrStoreOnline(id, 0, 9, model);
        return "module-food";
    }

    @GetMapping("/food/online-store/tag")
    public String getFoodOnlineStoreByTag(
            @RequestParam("tagId") Integer tagId,
            @RequestParam("id") Integer id,
            Model model
    ) {
        addAttrStoreOnline(id, tagId, 9, model);
        return "module-food";
    }

    @GetMapping("/food/online-store/food-detail")
    public String getFoodDetail(
            @RequestParam("id") Integer id,
            @RequestParam("itemId") Integer itemId,
            @RequestParam("tagId") Integer tagId,
            Model model
    ) {
        addAttrStoreOnline(id, tagId, 9, model);
        FoodItem item = foodItemService.getOne(itemId);
        model.addAttribute("item", item);
        return "module-food";
    }

    private void addAttrStoreOnline(int id, int tagId, int page, Model model) {
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOne(id);
        List<FoodTag> foodStoreOnlineTagList = foodTagService.getAllByStoreOnlineId(id);
        List<FoodStoreOnlineRate> listComment = foodStoreOnlineRateService.getAllCommentByStoreOnlineId(id);
        List<FoodItem> foodItemList;
        if (tagId == 0) {
            foodItemList = foodItemService.getAllByStoreOnlineId(id);
        } else {
            foodItemList = foodItemService.getAllByStoreOnlineIdAndTagId(id, tagId);
        }
        model.addAttribute("tagId", tagId);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("foodStoreOnlineTagList", foodStoreOnlineTagList);
        model.addAttribute("foodItemList", foodItemList);
        model.addAttribute("listComment", listComment);
        model.addAttribute("userDetailService", userDetailService);
        model.addAttribute("page", page);
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
}
