package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandcore.util.Format;
import com.hola.holalandfood.entity.*;
import com.hola.holalandfood.service.*;
import com.hola.holalandfood.view.FoodCountSttOrder;
import com.hola.holalandweb.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

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
    private final FoodCountSttOrderService foodCountSttOrderService;

    @Autowired
    public FoodController(
            FoodStoreOnlineService foodStoreOnlineService,
            FoodTypeService foodTypeService,
            FoodStoreOnlineTagService foodStoreOnlineTagService,
            FoodTagService foodTagService,
            FoodItemService foodItemService,
            FoodStoreOnlineRateService foodStoreOnlineRateService,
            UserDetailService userDetailService,
            FoodReportService foodReportService,
            FoodOrderService foodOrderService,
            SttFoodService sttFoodService,
            FoodOrderDetailService foodOrderDetailService,
            FoodCountSttOrderService foodCountSttOrderService) {
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
        this.foodCountSttOrderService = foodCountSttOrderService;
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
        addAttrStoreOnline(id, 0, 9, model);
        model.addAttribute("cart", true);
        return "module-food";
    }

    @GetMapping("/store/tag")
    public String getFoodOnlineStoreByTag(@RequestParam("tagId") Integer tagId, @RequestParam("id") Integer id, Model model) {
        addAttrStoreOnline(id, tagId, 9, model);
        return "module-food";
    }

    @GetMapping("/store/detail")
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
        List<FoodItem> foodItemList = (tagId == 0)
                        ? foodItemService.getAllByStoreOnlineId(id)
                        : foodItemService.getAllByStoreOnlineIdAndTagId(id, tagId);;

        model.addAttribute("tagId", tagId);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("foodStoreOnlineTagList", foodStoreOnlineTagList);
        model.addAttribute("foodItemList", foodItemList);
        model.addAttribute("listComment", listComment);
        model.addAttribute("listReport", listReport);
        model.addAttribute("userDetailService", userDetailService);
        model.addAttribute("page", page);
    }

    @GetMapping("/offline-store")
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
        addAttrOrder(currentUser, 0, model);
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
        addAttrOrder(currentUser, sttCode, model);
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
        addAttrOrder(currentUser, sttCode, model);
        List<FoodOrderDetail> foodOrderDetailList = foodOrderDetailService.getAllByOrderId(orderId);
        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByOrderId(orderId);
        FoodOrder foodOrder = foodOrderService.getOne(orderId);
        model.addAttribute("userNote", foodOrder.getFoodOrderNote());
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("foodOrderDetailList", foodOrderDetailList);
        model.addAttribute("foodStoreOnline", foodStoreOnline);
        model.addAttribute("foodItemService", foodItemService);
        return "module-food";
    }

    public void addAttrOrder(CustomUser currentUser, int sttCode, Model model) {
        List<SttFood> sttTypeList = sttFoodService.getAllHistoryOrder();
        List<FoodOrder> foodOrderList;
        List<FoodOrder> historyOrderList;

        // Get user role
        Collection<GrantedAuthority> authorities = currentUser.getAuthorities();
        boolean isSeller = authorities.contains(new SimpleGrantedAuthority("ROLE_SELLER"));

        if (isSeller) {
            foodOrderList = foodOrderService.getAllSellerOrderByUserIdAndStatus(
                    currentUser.getId(),
                    Constants.STT_FOOD_CODE_PENDING_APPROVAL,
                    Constants.STT_FOOD_CODE_APPROVED
            );
            if (sttCode == 0) {
                historyOrderList = foodOrderService.getAllSellerOrderByUserIdAndStatus(
                        currentUser.getId(),
                        Constants.STT_FOOD_CODE_REJECT,
                        Constants.STT_FOOD_CODE_COMPLETE,
                        Constants.STT_FOOD_CODE_EXPIRED
                );
            } else {
                historyOrderList = foodOrderService.getAllSellerOrderByUserIdAndStatus(currentUser.getId(), sttCode);
            }
            model.addAttribute("page", 4);
        } else {
            foodOrderList = foodOrderService.getAllUserOrderByUserIdAndStatus(
                    currentUser.getId(),
                    Constants.STT_FOOD_CODE_PENDING_APPROVAL,
                    Constants.STT_FOOD_CODE_APPROVED
            );
            if (sttCode == 0) {
                historyOrderList = foodOrderService.getAllUserOrderByUserIdAndStatus(
                        currentUser.getId(),
                        Constants.STT_FOOD_CODE_REJECT,
                        Constants.STT_FOOD_CODE_COMPLETE,
                        Constants.STT_FOOD_CODE_EXPIRED
                );
            } else {
                historyOrderList = foodOrderService.getAllUserOrderByUserIdAndStatus(currentUser.getId(), sttCode);
            }
            model.addAttribute("foodReportService", foodReportService);
            model.addAttribute("page", 3);
        }

        FoodCountSttOrder foodCountSttOrder;
        if(isSeller) {
            FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByUserId(currentUser.getId());
            foodCountSttOrder = foodCountSttOrderService.getCountSttOrderSeller(foodStoreOnline.getFoodStoreOnlineId());
        } else {
            foodCountSttOrder = foodCountSttOrderService.getCountSttOrderStudent(currentUser.getId());
        }

        model.addAttribute("format", new Format());
        model.addAttribute("sttCode", sttCode);
        model.addAttribute("sttTypeList", sttTypeList);
        model.addAttribute("foodOrderList", foodOrderList);
        model.addAttribute("historyOrderList", historyOrderList);
        model.addAttribute("foodCountSttOrder", foodCountSttOrder);
    }

    @GetMapping("/order/update-status-food")
    public String updateSttFoodOrder(@RequestParam("orderId") Integer foodOrderId) {
        FoodOrder foodOrder = FoodOrder.builder()
                .foodOrderId(foodOrderId)
                .sttFoodCode(Constants.STT_FOOD_CODE_EXPIRED)
                .build();

        boolean isCheck = foodOrderService.updateSttFood(foodOrder);
        if (isCheck) {
            return "redirect:" + "/food/order";
        } else {
            return "404";
        }
    }

    @PostMapping("/order/report")
    public String postUserReportOrder(
            @RequestParam("foodStoreOnlineId") int storeId,
            @RequestParam("foodOrderId") int orderId,
            @RequestParam("reportContent") String content,
            Authentication authentication
    ) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }

        Timestamp currentDate = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        FoodReport foodReport = FoodReport.builder()
                .userId(currentUser.getId())
                .foodOrderId(orderId)
                .foodStoreOnlineId(storeId)
                .foodReportContent(content)
                .foodReportCreateDate(currentDate)
                .foodReportDeleted(false)
                .build();
        boolean isCheck = foodReportService.save(foodReport);
        if (isCheck) {
            return "redirect:" + "/food/order";
        } else {
            return "404";
        }
    }

    @PostMapping("/order/reject")
    public String addRejectReasonOrder(
            @RequestParam("orderId") int orderId,
            @RequestParam("reasonReject") String reasonReject
    ) {
        FoodOrder newFoodOrder = FoodOrder.builder()
                .foodOrderId(orderId)
                .foodOrderReasonReject(reasonReject)
                .sttFoodCode(Constants.STT_FOOD_CODE_REJECT)
                .build();

        boolean isCheck = foodOrderService.addReasonReject(newFoodOrder);
        if (isCheck) {
            return "redirect:" + "/food/order";
        } else {
            return "404";
        }
    }

    @GetMapping("/order/delete-report")
    public String deleteReportOrder(@RequestParam("reportId") int reportId) {
        boolean isCheck = foodReportService.delete(reportId);
        if (isCheck) {
            return "redirect:" + "/food/order";
        } else {
            return "404";
        }
    }
}
