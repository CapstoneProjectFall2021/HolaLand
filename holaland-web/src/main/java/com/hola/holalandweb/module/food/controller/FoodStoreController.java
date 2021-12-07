package com.hola.holalandweb.module.food.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandcore.util.Format;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.entity.FoodReport;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodOrderDetailService;
import com.hola.holalandfood.service.FoodOrderService;
import com.hola.holalandfood.service.FoodReportService;
import com.hola.holalandfood.service.FoodStoreOnlineRateService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodTagService;
import com.hola.holalandfood.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/food/store")
public class FoodStoreController {

    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodTagService foodTagService;
    private final FoodItemService foodItemService;
    private final FoodStoreOnlineRateService foodStoreOnlineRateService;
    private final UserDetailService userDetailService;
    private final FoodReportService foodReportService;
    private final FoodOrderService foodOrderService;
    private final FoodOrderDetailService foodOrderDetailService;

    @Autowired
    public FoodStoreController(
            FoodStoreOnlineService foodStoreOnlineService,
            FoodTagService foodTagService,
            FoodItemService foodItemService,
            FoodStoreOnlineRateService foodStoreOnlineRateService,
            UserDetailService userDetailService,
            FoodReportService foodReportService,
            FoodOrderService foodOrderService,
            FoodOrderDetailService foodOrderDetailService
    ) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTagService = foodTagService;
        this.foodItemService = foodItemService;
        this.foodStoreOnlineRateService = foodStoreOnlineRateService;
        this.userDetailService = userDetailService;
        this.foodReportService = foodReportService;
        this.foodOrderService = foodOrderService;
        this.foodOrderDetailService = foodOrderDetailService;
    }

    @GetMapping("")
    public String goToOnlineStore(@RequestParam("id") Integer id, Model model) {
        FoodStoreOnlineRate rate = FoodStoreOnlineRate.builder().build();
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOne(id);

        addAttrStoreOnline(foodStoreOnline, 0, 9, model);
        model.addAttribute("newRate", rate);
        return "module-food";
    }

    @PostMapping("/rate")
    public String rate(
            @ModelAttribute("newRate") FoodStoreOnlineRate newRate,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        if(newRate.getFoodStoreOnlineRatePoint() != 0 && newRate.getFoodStoreOnlineRateComment().length() != 0) {
            Timestamp currentDate = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            newRate.setUserId(currentUser.getId());
            newRate.setFoodStoreOnlineRateCreateTime(currentDate);
            newRate.setFoodStoreOnlineDeleted(false);
            boolean isCheck = foodStoreOnlineRateService.insert(newRate);
            if (isCheck) {
                return "redirect:" + "/food/store?id=" + newRate.getFoodStoreOnlineId();
            } else {
                return "404";
            }
        } else {
            return "404";
        }
    }

    @GetMapping("/report/order/detail")
    public String getFoodOrderDetailReport(@RequestParam("orderId") Integer orderId, Model model) {
        List<FoodOrderDetail> foodOrderDetailReport = foodOrderDetailService.getAllByOrderId(orderId);
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByOrderId(orderId);

        addAttrStoreOnline(foodStoreOnline, 0, 9, model);
        model.addAttribute("foodOrderDetailReport", foodOrderDetailReport);
        model.addAttribute("tab", 3);
        return "module-food";
    }

    @GetMapping("/tag")
    public String getFoodOnlineStoreByTag(@RequestParam("storeId") Integer storeId, @RequestParam("tagId") Integer tagId, Model model) {
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOne(storeId);
        addAttrStoreOnline(foodStoreOnline, tagId, 9, model);
        return "module-food";
    }

    private void addAttrStoreOnline(FoodStoreOnline foodStoreOnline, int tagId, int page, Model model) {
        List<FoodTag> foodStoreOnlineTagList = foodTagService.getAllByStoreOnlineId(foodStoreOnline.getFoodStoreOnlineId());
        List<FoodStoreOnlineRate> listComment = foodStoreOnlineRateService.getAllCommentByStoreOnlineId(foodStoreOnline.getFoodStoreOnlineId());
        List<FoodReport> listReport = foodReportService.getAllByOrderId(foodStoreOnline.getFoodStoreOnlineId());
        List<FoodItem> foodItemList = (tagId == 0)
                ? foodItemService.getAllByStoreOnlineId(foodStoreOnline.getFoodStoreOnlineId())
                : foodItemService.getAllByStoreOnlineIdAndTagId(foodStoreOnline.getFoodStoreOnlineId(), tagId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = 0;
        if(auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            CustomUser currentUser = (CustomUser) auth.getPrincipal();
            userId = currentUser.getId();
        }
        model.addAttribute("userId", userId);
        model.addAttribute("tagId", tagId);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("foodStoreOnlineTagList", foodStoreOnlineTagList);
        model.addAttribute("foodItemList", foodItemList);
        model.addAttribute("listComment", listComment);
        model.addAttribute("listReport", listReport);
        model.addAttribute("userDetailService", userDetailService);  // get name off user in tab comment & tab report
        model.addAttribute("foodOrderService", foodOrderService);
        model.addAttribute("format", new Format());
        model.addAttribute("page", page);
    }
}
