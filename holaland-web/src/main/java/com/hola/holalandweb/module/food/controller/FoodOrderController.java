package com.hola.holalandweb.module.food.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.service.UserAddressService;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandcore.util.Format;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodItemCart;
import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.entity.FoodReport;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.SttFood;
import com.hola.holalandfood.service.FoodCountSttOrderService;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodOrderDetailService;
import com.hola.holalandfood.service.FoodOrderService;
import com.hola.holalandfood.service.FoodReportService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.SttFoodService;
import com.hola.holalandfood.view.FoodCountSttOrder;
import com.hola.holalandweb.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/food/order")
public class FoodOrderController {

    private final FoodItemService foodItemService;
    private final SttFoodService sttFoodService;
    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodCountSttOrderService foodCountSttOrderService;
    private final UserAddressService userAddressService;
    private final FoodOrderService foodOrderService;
    private final FoodOrderDetailService foodOrderDetailService;
    private final UserDetailService userDetailService;
    private final FoodReportService foodReportService;

    @Autowired
    public FoodOrderController(
            FoodItemService foodItemService,
            SttFoodService sttFoodService,
            FoodStoreOnlineService foodStoreOnlineService,
            FoodCountSttOrderService foodCountSttOrderService,
            UserAddressService userAddressService,
            FoodOrderService foodOrderService,
            FoodOrderDetailService foodOrderDetailService,
            UserDetailService userDetailService,
            FoodReportService foodReportService
    ) {
        this.foodItemService = foodItemService;
        this.sttFoodService = sttFoodService;
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodCountSttOrderService = foodCountSttOrderService;
        this.userAddressService = userAddressService;
        this.foodOrderService = foodOrderService;
        this.foodOrderDetailService = foodOrderDetailService;
        this.userDetailService = userDetailService;
        this.foodReportService = foodReportService;
    }

    // User order
    @GetMapping("")
    public String goToUserOrder(Model model, Authentication authentication) {
        addAttrUserOrder(0, model, authentication);
        return "module-food";
    }

    @GetMapping("/type")
    public String getUserOrderByType(@RequestParam("sttCode") Integer sttCode, Model model, Authentication authentication) {
        addAttrUserOrder(sttCode, model, authentication);
        return "module-food";
    }

    @GetMapping("/detail")
    public String getUserOrderDetail(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("sttCode") Integer sttCode,
            @RequestParam("orderStatus") Integer orderStatus,
            Model model,
            Authentication authentication
    ) {
        addAttrUserOrder(sttCode, model, authentication);

        List<FoodOrderDetail> foodOrderDetailList = foodOrderDetailService.getAllByOrderId(orderId);

        // Get store id for click food name in modal
        int foodStoreOnlineId = foodStoreOnlineService.getOneByOrderId(orderId).getFoodStoreOnlineId();
        int sellerId = foodStoreOnlineService.getOneByOrderId(orderId).getUserId();

        // Add more attr for modal order detail
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("foodOrderDetailList", foodOrderDetailList);
        model.addAttribute("foodStoreOnlineId", foodStoreOnlineId);
        model.addAttribute("sellerPhone", userAddressService.getOneByUserId(sellerId).getUserPhone());
        return "module-food";
    }

    private void addAttrUserOrder(int sttCode, Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

        List<FoodOrder> foodOrderList = foodOrderService.getAllUserOrderByUserIdAndStatus(
                currentUser.getId(),
                Constants.STT_FOOD_CODE_PENDING_APPROVAL,
                Constants.STT_FOOD_CODE_APPROVED
        );

        List<FoodOrder> historyOrderList;
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

        FoodCountSttOrder foodCountSttOrder = foodCountSttOrderService.getCountSttOrderStudent(currentUser.getId());

        model.addAttribute("format", new Format());
        model.addAttribute("sttCode", sttCode);
        model.addAttribute("foodOrderList", foodOrderList);
        model.addAttribute("historyOrderList", historyOrderList);
        model.addAttribute("foodReportService", foodReportService);
        model.addAttribute("foodCountSttOrder", foodCountSttOrder);
        model.addAttribute("page", 3);
    }

    // Seller order
    @GetMapping("/manage")
    public String goToSellerManageOrder(Model model, Authentication authentication) {
        addAttrSellerOrder(0, model, authentication);
        return "module-food-manage-store";
    }

    @GetMapping("/manage/type")
    public String getSellerOrderByType(@RequestParam("sttCode") Integer sttCode, Model model, Authentication authentication) {
        addAttrSellerOrder(sttCode, model, authentication);
        return "module-food-manage-store";
    }

    @GetMapping("/manage/detail")
    public String getSellerOrderDetail(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("sttCode") Integer sttCode,
            @RequestParam("orderStatus") Integer orderStatus,
            Model model,
            Authentication authentication
    ) {
        addAttrSellerOrder(sttCode, model, authentication);

        List<FoodOrderDetail> foodOrderDetailList = foodOrderDetailService.getAllByOrderId(orderId);

        // Get store id for click food name in modal
        int foodStoreOnlineId = foodStoreOnlineService.getOneByOrderId(orderId).getFoodStoreOnlineId();

        FoodOrder foodOrder = foodOrderService.getOne(orderId);

        // Add more attr for modal order detail
        model.addAttribute("userNote", foodOrder.getFoodOrderNote());
        model.addAttribute("userAddress", userAddressService.getOneByUserId(foodOrder.getUserId()));
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("foodOrderDetailList", foodOrderDetailList);
        model.addAttribute("foodStoreOnlineId", foodStoreOnlineId);
        return "module-food-manage-store";
    }

    private void addAttrSellerOrder(int sttCode, Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

        List<FoodOrder> foodOrderList = foodOrderService.getAllSellerOrderByUserIdAndStatus(
                currentUser.getId(),
                Constants.STT_FOOD_CODE_PENDING_APPROVAL,
                Constants.STT_FOOD_CODE_APPROVED
        );

        List<FoodOrder> historyOrderList;
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

        FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOneByUserId(currentUser.getId());
        FoodCountSttOrder foodCountSttOrder = foodCountSttOrderService.getCountSttOrderSeller(foodStoreOnline.getFoodStoreOnlineId());

        model.addAttribute("format", new Format());
        model.addAttribute("sttCode", sttCode);
        model.addAttribute("foodOrderList", foodOrderList);
        model.addAttribute("historyOrderList", historyOrderList);
        model.addAttribute("foodReportService", foodReportService);
        model.addAttribute("foodCountSttOrder", foodCountSttOrder);
        model.addAttribute("page", 1);
    }

    @GetMapping("/cancel")
    public String userCancelOrder(@RequestParam("orderId") Integer foodOrderId) {
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

    @PostMapping("/report")
    public String userReportOrder(
            @RequestParam("foodStoreOnlineId") int storeId,
            @RequestParam("foodOrderId") int orderId,
            @RequestParam("reportContent") String content,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

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

    @PostMapping("/reject")
    public String sellerRejectOrder(
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
            return "redirect:" + "/food/order/manage";
        } else {
            return "404";
        }
    }

    @GetMapping("/report/delete")
    public String userDeleteReportOrder(@RequestParam("reportId") int reportId) {
        boolean isCheck = foodReportService.delete(reportId);
        if (isCheck) {
            return "redirect:" + "/food/order";
        } else {
            return "404";
        }
    }

    @GetMapping("/cart")
    public String goToCart(Model model) {
        model.addAttribute("page", 8);
        return "module-food";
    }

    @GetMapping("add-to-cart")
    public ResponseEntity<Integer> getCourseById(
            @RequestParam("foodId") int foodId,
            @RequestParam("storeId") int storeId,
            HttpSession session
    ) {
        FoodItem foodItem = foodItemService.getOne(foodId);
        FoodItemCart foodItemCart = FoodItemCart.builder()
                .foodId(foodId)
                .foodName(foodItem.getFoodItemName())
                .foodImage(foodItem.getFoodItemImage())
                .unitPrice(foodItem.getFoodItemPrice())
                .quantity(1)
                .totalPrice(foodItem.getFoodItemPrice())
                .build();

        Map<String, Object> mapFoodOrder = (Map<String, Object>) session.getAttribute("mapFoodOrder");
        List<FoodItemCart> listFoodOrder;

        boolean flat = true;
        if (mapFoodOrder == null) {
            mapFoodOrder = new LinkedHashMap<>();
            listFoodOrder = new ArrayList<>();
            listFoodOrder.add(foodItemCart);
        } else {
            listFoodOrder = (List<FoodItemCart>) mapFoodOrder.get("listFoodOrder");
            for (FoodItemCart f : listFoodOrder) {
                if (f.getFoodId() == foodId) {
                    f.setQuantity(f.getQuantity() + 1);
                    f.setTotalPrice(f.getQuantity() * f.getUnitPrice());
                    flat = false;
                }
            }
            // add new item
            if (flat) {
                listFoodOrder.add(foodItemCart);
                flat = true;
            }
        }

        mapFoodOrder.put("listFoodOrder", listFoodOrder);
        mapFoodOrder.put("totalMoney", getTotalMoney(listFoodOrder));

        session.setAttribute("mapFoodOrder", mapFoodOrder);
        return new ResponseEntity<>(countQuantity(listFoodOrder), HttpStatus.OK);
    }

    private int countQuantity(List<FoodItemCart> listFoodOrder) {
        int count = 0;
        for (FoodItemCart foodItemCart : listFoodOrder) {
            count += foodItemCart.getQuantity();
        }
        return count;
    }

    private double getTotalMoney(List<FoodItemCart> listFoodOrder) {
        double total = 0;
        for (FoodItemCart foodItemCart : listFoodOrder) {
            total += foodItemCart.getTotalPrice();
        }
        return total;
    }
}
