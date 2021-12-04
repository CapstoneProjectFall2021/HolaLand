package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.service.UserAddressService;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandcore.util.Format;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.entity.FoodReport;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.entity.FoodType;
import com.hola.holalandfood.service.FoodCountSttOrderService;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodOrderDetailService;
import com.hola.holalandfood.service.FoodOrderService;
import com.hola.holalandfood.service.FoodReportService;
import com.hola.holalandfood.service.FoodStoreOnlineRateService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodStoreOnlineTagService;
import com.hola.holalandfood.service.FoodTagService;
import com.hola.holalandfood.service.FoodTypeService;
import com.hola.holalandfood.service.SttFoodService;
import com.hola.holalandweb.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/food")
public class FoodController {

    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodTypeService foodTypeService;
    private final FoodTagService foodTagService;
    private final FoodItemService foodItemService;
    private final FoodStoreOnlineRateService foodStoreOnlineRateService;
    private final UserDetailService userDetailService;
    private final FoodReportService foodReportService;
    private final FoodOrderService foodOrderService;
    private final FoodOrderDetailService foodOrderDetailService;

    @Autowired
    public FoodController(
            FoodStoreOnlineService foodStoreOnlineService,
            FoodTypeService foodTypeService,
            FoodTagService foodTagService,
            FoodItemService foodItemService,
            FoodStoreOnlineRateService foodStoreOnlineRateService,
            UserDetailService userDetailService,
            FoodReportService foodReportService,
            FoodOrderService foodOrderService,
            FoodOrderDetailService foodOrderDetailService
    ) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTypeService = foodTypeService;
        this.foodTagService = foodTagService;
        this.foodItemService = foodItemService;
        this.foodStoreOnlineRateService = foodStoreOnlineRateService;
        this.userDetailService = userDetailService;
        this.foodReportService = foodReportService;
        this.foodOrderService = foodOrderService;
        this.foodOrderDetailService = foodOrderDetailService;
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
    public String getFoodsByType(@RequestParam("typeId") Integer typeId, Model model) {
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


    @GetMapping("/store")
    public String goToOnlineStore(@RequestParam("id") Integer id, Model model) {
        FoodStoreOnlineRate rate = FoodStoreOnlineRate.builder().build();
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOne(id);

        addAttrStoreOnline(foodStoreOnline, 0, 9, model);
        model.addAttribute("newRate", rate);
        return "module-food";
    }

    @PostMapping("/store/rate")
    public String insertNewRate(
            @ModelAttribute("newRate") FoodStoreOnlineRate newRate,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

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
    }

    @GetMapping("/store/report/order/detail")
    public String getFoodOrderDetailReport(@RequestParam("orderId") Integer orderId, Model model) {
        List<FoodOrderDetail> foodOrderDetailReport = foodOrderDetailService.getAllByOrderId(orderId);
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByOrderId(orderId);

        addAttrStoreOnline(foodStoreOnline, 0, 9, model);
        model.addAttribute("foodOrderDetailReport", foodOrderDetailReport);
        model.addAttribute("tab", 3);
        return "module-food";
    }

    @GetMapping("/store/tag")
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

        model.addAttribute("tagId", tagId);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("foodStoreOnlineTagList", foodStoreOnlineTagList);
        model.addAttribute("foodItemList", foodItemList);
        model.addAttribute("listComment", listComment);
        model.addAttribute("listReport", listReport);
        model.addAttribute("userDetailService", userDetailService);  // get name off user in tab comment & report
        model.addAttribute("foodOrderService", foodOrderService);
        model.addAttribute("format", new Format());
        model.addAttribute("page", page);
    }

    @GetMapping("/offline-store")
    public String goToOfflineStore(Model model) {
        model.addAttribute("page", 2);
        return "module-food";
    }
}
