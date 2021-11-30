package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandfood.entity.*;
import com.hola.holalandfood.service.*;
import com.hola.holalandweb.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {

    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodTypeService foodTypeService;
    private final FoodStoreOnlineTagService foodStoreOnlineTagService;
    private final FoodTagService foodTagService;
    private final FoodItemService foodItemService;
    private final FoodStoreOnlineRateService foodStoreOnlineRateService;
    private final UserDetailService userDetailService;
    private final FoodReportService foodReportService;
    private final FoodOrderService foodOrderService;
    private final SttFoodService sttFoodService;
    private final FoodOrderDetailService foodOrderDetailService;

    @Autowired
    public FoodController(FoodStoreOnlineService foodStoreOnlineService,
                          FoodTypeService foodTypeService,
                          FoodStoreOnlineTagService foodStoreOnlineTagService,
                          FoodTagService foodTagService,
                          FoodItemService foodItemService,
                          FoodStoreOnlineRateService foodStoreOnlineRateService,
                          UserDetailService userDetailService,
                          FoodReportService foodReportService,
                          FoodOrderService foodOrderService,
                          SttFoodService sttFoodService,
                          FoodOrderDetailService foodOrderDetailService) {
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodTypeService = foodTypeService;
        this.foodStoreOnlineTagService = foodStoreOnlineTagService;
        this.foodTagService = foodTagService;
        this.foodItemService = foodItemService;
        this.foodStoreOnlineRateService = foodStoreOnlineRateService;
        this.userDetailService = userDetailService;
        this.foodReportService = foodReportService;
        this.foodOrderService = foodOrderService;
        this.sttFoodService = sttFoodService;
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


    @GetMapping("/online-store")
    public String goToOnlineStore(@RequestParam("id") Integer id, Model model) {
        addAttrStoreOnline(id, 0, 9, model);
        return "module-food";
    }

    @GetMapping("/online-store/tag")
    public String getFoodOnlineStoreByTag(
            @RequestParam("tagId") Integer tagId,
            @RequestParam("id") Integer id,
            Model model
    ) {
        addAttrStoreOnline(id, tagId, 9, model);
        return "module-food";
    }

    @GetMapping("/online-store/food-detail")
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
        List<FoodReport> listReport = foodReportService.getAllByOrderId(id);
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
        model.addAttribute("listReport", listReport);
        model.addAttribute("userDetailService", userDetailService);
        model.addAttribute("page", page);
    }

    @GetMapping("/list-offline-store")
    public String goToOfflineStore(Model model) {
        model.addAttribute("page", 2);
        return "module-food";
    }

    @GetMapping("/order")
    public String goToUserOrder(Model model, Authentication authentication) {
        CustomUser currentUser;

        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        addAttrUserOrder(currentUser.getId(), 0, 3, model);
        return "module-food";
    }

    public void addAttrUserOrder(int userId, int sttCode, int page, Model model) {
        List<FoodOrder> foodOrderList = foodOrderService.getAllUserOrderByUserIdAndStatus(userId,
                Constants.STT_FOOD_CODE_PENDING_APPROVAL,
                Constants.STT_FOOD_CODE_APPROVED);
        List<SttFood> sttTypeList = sttFoodService.getAllByHistoryOrder();
        List<FoodOrder> foodOrderedList;
        if (sttCode == 0) {
            foodOrderedList = foodOrderService.getAllUserOrderByUserIdAndStatus(userId,
                    Constants.STT_FOOD_CODE_REJECT,
                    Constants.STT_FOOD_CODE_COMPLETE,
                    Constants.STT_FOOD_CODE_EXPIRED);
        } else {
            foodOrderedList = foodOrderService.getAllUserOrderByUserIdAndStatus(userId, sttCode);
        }
        model.addAttribute("sttCode", sttCode);
        model.addAttribute("sttTypeList", sttTypeList);
        model.addAttribute("foodOrderList", foodOrderList);
        model.addAttribute("foodOrderedList", foodOrderedList);
        model.addAttribute("page", page);
    }

    @GetMapping("/order/updateSttFood")
    public String updateSttFoodOrder(
            @RequestParam("orderId") Integer foodOrderId) {
        FoodOrder foodOrder = FoodOrder.builder().build();
        foodOrder.setFoodOrderId(foodOrderId);
        foodOrder.setSttFoodCode(Constants.STT_FOOD_CODE_EXPIRED);
        boolean isCheck = foodOrderService.updateSttFood(foodOrder);
        if (isCheck) {
            return "redirect:" + "/food/order";
        } else {
            return "404";
        }
    }

    @GetMapping("/order/reason-reject")
    public String getReasonRejectFoodOrder(
            @RequestParam("orderId") Integer foodOrderId,
            @RequestParam("sttCode") Integer sttCode,
            Authentication authentication,
            Model model
    ) {
        CustomUser currentUser;

        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        Collection<GrantedAuthority> authorities = currentUser.getAuthorities();
        boolean isSeller = authorities.contains(new SimpleGrantedAuthority("ROLE_SELLER"));
        if (isSeller) {
            addAttrSellerOrder(currentUser.getId(), sttCode, 4, model);
        } else {
            addAttrUserOrder(currentUser.getId(), sttCode, 3, model);
        }
        FoodOrder foodOrder = foodOrderService.getOne(foodOrderId);
        model.addAttribute("reasonReject", foodOrder.getFoodOrderReasonReject());
        return "module-food";
    }

    @GetMapping("/order/type")
    public String getFoodOrderedByType(@RequestParam("sttCode") Integer sttCode, Model model, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        addAttrUserOrder(currentUser.getId(), sttCode, 3, model);
        return "module-food";
    }

    @GetMapping("/order/detail")
    public String getFoodOrderDetail(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("sttCode") Integer sttCode,
            @RequestParam("orderStatus") Integer orderStatus,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        Collection<GrantedAuthority> authorities = currentUser.getAuthorities();
        boolean isSeller = authorities.contains(new SimpleGrantedAuthority("ROLE_SELLER"));
        if (isSeller) {
            addAttrSellerOrder(currentUser.getId(), sttCode, 4, model);
            FoodOrder foodOrder = foodOrderService.getOne(orderId);
            model.addAttribute("buyerNote", foodOrder.getFoodOrderNote());
        } else {
            addAttrUserOrder(currentUser.getId(), sttCode, 3, model);
        }
        List<FoodOrderDetail> foodOrderDetailList = foodOrderDetailService.getAllByOrderId(orderId);
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByOrderId(orderId);
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("foodOrderDetailList", foodOrderDetailList);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("foodItemService", foodItemService);
        return "module-food";
    }

    @GetMapping("/order/report-order")
    public String getFormReportOrder(
            @RequestParam("storeId") Integer storeId,
            @RequestParam("orderId") Integer orderId,
            @RequestParam("sttCode") Integer sttCode,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        addAttrUserOrder(currentUser.getId(), sttCode, 3, model);
        FoodReport newFoodReport = FoodReport.builder().build();
        model.addAttribute("newFoodReport", newFoodReport);
        model.addAttribute("userId", currentUser.getId());
        model.addAttribute("storeId", storeId);
        model.addAttribute("orderId", orderId);
        return "module-food";
    }

    @PostMapping("/report-order")
    public String postUserReportOrder(
            @ModelAttribute("newFoodReport") FoodReport newFoodReport,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        Timestamp currentDate = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        newFoodReport.setFoodReportCreateDate(currentDate);
        newFoodReport.setFoodReportDeleted(false);
        boolean isCheck = foodReportService.save(newFoodReport);
        if (isCheck) {
            return "redirect:" + "/food/order";
        } else {
            return "404";
        }
    }

    @GetMapping("/seller-order")
    public String goToSellerOrder(Model model, Authentication authentication) {
        CustomUser currentUser;

        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        addAttrSellerOrder(currentUser.getId(), 0, 4, model);
        return "module-food";
    }

    public void addAttrSellerOrder(int userId, int sttCode, int page, Model model) {
        List<FoodOrder> foodOrderList = foodOrderService.getAllSellerOrderByUserIdAndStatus(userId,
                Constants.STT_FOOD_CODE_PENDING_APPROVAL,
                Constants.STT_FOOD_CODE_APPROVED);
        List<SttFood> sttTypeList = sttFoodService.getAllByHistoryOrder();
        List<FoodOrder> foodOrderedList;
        if (sttCode == 0) {
            foodOrderedList = foodOrderService.getAllSellerOrderByUserIdAndStatus(userId,
                    Constants.STT_FOOD_CODE_REJECT,
                    Constants.STT_FOOD_CODE_COMPLETE,
                    Constants.STT_FOOD_CODE_EXPIRED);
        } else {
            foodOrderedList = foodOrderService.getAllSellerOrderByUserIdAndStatus(userId, sttCode);
        }
        model.addAttribute("sttCode", sttCode);
        model.addAttribute("sttTypeList", sttTypeList);
        model.addAttribute("foodOrderList", foodOrderList);
        model.addAttribute("foodOrderedList", foodOrderedList);
        model.addAttribute("page", page);
    }

    @GetMapping("/seller-order/type")
    public String getFoodSellerOrderedByType(@RequestParam("sttCode") Integer sttCode, Model model, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        addAttrSellerOrder(currentUser.getId(), sttCode, 4, model);
        return "module-food";
    }

    @GetMapping("/order/note")
    public String getNoteFoodOrder(
            @RequestParam("orderId") Integer foodOrderId,
            @RequestParam("sttCode") Integer sttCode,
            Authentication authentication,
            Model model
    ) {
        CustomUser currentUser;

        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        addAttrSellerOrder(currentUser.getId(), sttCode, 4, model);
        FoodOrder foodOrder = foodOrderService.getOne(foodOrderId);
        model.addAttribute("buyerNote", foodOrder.getFoodOrderNote());
        return "module-food";
    }

    @GetMapping("/order/reject-order")
    public String getFormRejectOrder(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("sttCode") Integer sttCode,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        addAttrSellerOrder(currentUser.getId(), sttCode, 4, model);
        model.addAttribute("orderId", orderId);
        return "module-food";
    }

    @PostMapping("/reject-order")
    public String addRejectReasonOrder(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("content") String content
    ) {
        FoodOrder foodOrder = FoodOrder.builder().build();
        foodOrder.setFoodOrderId(orderId);
        foodOrder.setSttFoodCode(Constants.STT_FOOD_CODE_REJECT);
        foodOrder.setFoodOrderReasonReject(content);
        boolean isCheck = foodOrderService.addReasonReject(foodOrder);
        if (isCheck) {
            return "redirect:" + "/food/seller-order";
        } else {
            return "404";
        }
    }
}
