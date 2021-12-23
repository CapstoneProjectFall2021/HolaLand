package com.hola.holalandweb.module.food.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.entity.UserAddress;
import com.hola.holalandcore.service.UserAddressService;
import com.hola.holalandcore.util.Format;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodItemCart;
import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodOrderDetailService;
import com.hola.holalandfood.service.FoodOrderService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/food/cart")
public class FoodCartController {

    private final String MAP_ORDER_IN_CART = "mapOrderInCart";
    private final String LIST_FOOD_ITEM_ORDER = "listFoodItemOrder";

    private final FoodItemService foodItemService;
    private final UserAddressService userAddressService;
    private final FoodStoreOnlineService foodStoreOnlineService;
    private final FoodOrderService foodOrderService;
    private final FoodOrderDetailService foodOrderDetailService;

    @Autowired
    public FoodCartController(
            FoodItemService foodItemService,
            UserAddressService userAddressService,
            FoodStoreOnlineService foodStoreOnlineService,
            FoodOrderService foodOrderService,
            FoodOrderDetailService foodOrderDetailService
    ) {
        this.foodItemService = foodItemService;
        this.userAddressService = userAddressService;
        this.foodStoreOnlineService = foodStoreOnlineService;
        this.foodOrderService = foodOrderService;
        this.foodOrderDetailService = foodOrderDetailService;
    }

    @GetMapping("")
    public String goToCart(Model model, Authentication authentication, HttpSession session) {
        backToCart(model, authentication, session);
        return "module-food";
    }

    @GetMapping("/add")
    public ResponseEntity<?> addToCart(
            @RequestParam("foodId") int foodId,
            @RequestParam("storeId") int storeId,
            HttpSession session,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        boolean isOwner = foodStoreOnlineService.checkUserIsOwner(currentUser.getId(), storeId);

        if (isOwner) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        FoodItem foodItem = foodItemService.getOne(foodId);
        FoodItemCart foodItemCart = FoodItemCart.builder()
                .foodId(foodId)
                .storeId(storeId)
                .foodName(foodItem.getFoodItemName())
                .foodImage(foodItem.getFoodItemImage())
                .unitPrice(foodItem.getFoodItemPrice())
                .quantity(1)
                .totalPrice(foodItem.getFoodItemPrice())
                .build();

        Map<String, Object> mapOrderInCart = (Map<String, Object>) session.getAttribute(MAP_ORDER_IN_CART);
        List<FoodItemCart> listFoodItemOrder;

        boolean flat = true;
        if (mapOrderInCart == null) {
            mapOrderInCart = new LinkedHashMap<>();
            listFoodItemOrder = new ArrayList<>();
            listFoodItemOrder.add(foodItemCart);
        } else {
            listFoodItemOrder = (List<FoodItemCart>) mapOrderInCart.get(LIST_FOOD_ITEM_ORDER);
            if (listFoodItemOrder.get(0).getStoreId() != storeId) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                for (FoodItemCart f : listFoodItemOrder) {
                    if (f.getFoodId() == foodId) {
                        f.setQuantity(f.getQuantity() + 1);
                        f.setTotalPrice(f.getQuantity() * f.getUnitPrice());
                        flat = false;
                    }
                }
                // add new item
                if (flat) {
                    listFoodItemOrder.add(foodItemCart);
                }
            }
        }
        setAttributeCart(mapOrderInCart, listFoodItemOrder, session);
        return new ResponseEntity<>(countQuantity(listFoodItemOrder), HttpStatus.OK);
    }

    private int countQuantity(List<FoodItemCart> listFoodItemOrder) {
        int count = 0;
        for (FoodItemCart foodItemCart : listFoodItemOrder) {
            count += foodItemCart.getQuantity();
        }
        return count;
    }

    private double getTotalMoney(List<FoodItemCart> listFoodItemOrder) {
        double total = 0;
        for (FoodItemCart foodItemCart : listFoodItemOrder) {
            total += foodItemCart.getTotalPrice();
        }
        return total;
    }

    @GetMapping("/delete")
    public String deleteCart(Model model, HttpSession session) {
        session.removeAttribute(MAP_ORDER_IN_CART);
        model.addAttribute("page", 8);
        return "redirect:" + "/food/cart";
    }

    @GetMapping("/delete/item")
    public String deleteItemInCart(
            @RequestParam("foodId") int foodId,
            HttpSession session
    ) {
        Map<String, Object> mapOrderInCart = (Map<String, Object>) session.getAttribute(MAP_ORDER_IN_CART);
        List<FoodItemCart> listFoodItemOrder = (List<FoodItemCart>) mapOrderInCart.get(LIST_FOOD_ITEM_ORDER);

        for (int i = 0; i < listFoodItemOrder.size(); i++) {
            if (listFoodItemOrder.get(i).getFoodId() == foodId) {
                listFoodItemOrder.remove(i);
            }
        }

        if (listFoodItemOrder.isEmpty()) {
            session.removeAttribute(MAP_ORDER_IN_CART);
        } else {
            setAttributeCart(mapOrderInCart, listFoodItemOrder, session);
        }
        return "redirect:" + "/food/cart";
    }

    @GetMapping("/update/quantity")
    public String updateQuantityItemInCart(
            @RequestParam("foodId") int foodId,
            @RequestParam("flag") int flag,
            HttpSession session
    ) {
        Map<String, Object> mapOrderInCart = (Map<String, Object>) session.getAttribute(MAP_ORDER_IN_CART);
        List<FoodItemCart> listFoodItemOrder = (List<FoodItemCart>) mapOrderInCart.get(LIST_FOOD_ITEM_ORDER);

        for (int i = 0; i < listFoodItemOrder.size(); i++) {
            FoodItemCart foodItemCart = listFoodItemOrder.get(i);
            if (foodItemCart.getFoodId() == foodId) {
                if (flag == 1) {
                    foodItemCart.setQuantity(foodItemCart.getQuantity() + 1);
                } else {
                    foodItemCart.setQuantity(foodItemCart.getQuantity() - 1);

                    if (foodItemCart.getQuantity() == 0) {
                        listFoodItemOrder.remove(i);
                    }
                }
                foodItemCart.setTotalPrice(foodItemCart.getQuantity() * foodItemCart.getUnitPrice());
            }
        }

        if (listFoodItemOrder.size() == 0 || listFoodItemOrder.isEmpty()) {
            session.removeAttribute(MAP_ORDER_IN_CART);
        } else {
            setAttributeCart(mapOrderInCart, listFoodItemOrder, session);
        }
        return "redirect:" + "/food/cart";
    }

    @PostMapping("/confirm")
    public String confirmCheckout(
            @RequestParam Map<String, Object> params,
            Model model,
            Authentication authentication,
            HttpSession session
    ) {
        String note = (String) params.get("note");
        boolean isSuccess = addOrder(authentication, session, note);
        if (isSuccess) {
            backToCart(model, authentication, session);
            return "redirect:" + "/food/cart/order/success";
        }
        backToCart(model, authentication, session);
        return "404";
    }

    @GetMapping("/order/success")
    public String orderSuccess(Model model) {
        model.addAttribute("page", 10);
        return "module-food";
    }

    @Transactional
    boolean addOrder(Authentication authentication, HttpSession session, String note) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        Map<String, Object> mapOrderInCart = (Map<String, Object>) session.getAttribute(MAP_ORDER_IN_CART);

        // Add to order
        Timestamp currentDate = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        FoodOrder foodOrder = FoodOrder.builder()
                .userId(currentUser.getId())
                .foodStoreOnlineId((Integer) mapOrderInCart.get("foodStoreOnlineId"))
                .sttFoodCode(1)
                .foodOrderTotalPrice(getTotalMoney((List<FoodItemCart>) mapOrderInCart.get(LIST_FOOD_ITEM_ORDER)))
                .foodOrderCreatedDate(currentDate)
                .foodOrderNote(note)
                .foodOrderDeleted(false)
                .build();

        int orderId = foodOrderService.save(foodOrder);
        if (orderId > 0) {
            // Add to order detail
            List<FoodItemCart> listFoodItemOrder = (List<FoodItemCart>) mapOrderInCart.get(LIST_FOOD_ITEM_ORDER);
            List<FoodOrderDetail> listFoodOrderDetail = new ArrayList<>();

            // map FoodItemCart to FoodOrderDetail
            for (FoodItemCart foodItemCart : listFoodItemOrder) {
                FoodOrderDetail foodOrderDetail = FoodOrderDetail.builder()
                        .foodOrderId(orderId)
                        .foodItemId(foodItemCart.getFoodId())
                        .foodItemName(foodItemCart.getFoodName())
                        .foodItemPrice(foodItemCart.getUnitPrice())
                        .foodItemQuantity(foodItemCart.getQuantity())
                        .build();
                listFoodOrderDetail.add(foodOrderDetail);
            }
            boolean isSuccess = foodOrderDetailService.save(listFoodOrderDetail);

            if (isSuccess) {
                session.removeAttribute(MAP_ORDER_IN_CART);
                return true;
            }
        }
        return false;
    }

    private void setAttributeCart(Map<String, Object> mapOrderInCart, List<FoodItemCart> listFoodItemOrder, HttpSession session) {
        if (listFoodItemOrder != null || listFoodItemOrder.isEmpty()) {
            FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOne(listFoodItemOrder.get(0).getStoreId());
            mapOrderInCart.put("foodStoreOnlineId", foodStoreOnline.getFoodStoreOnlineId());
            mapOrderInCart.put("foodStoreOnlineName", foodStoreOnline.getFoodStoreOnlineName());
        }
        mapOrderInCart.put(LIST_FOOD_ITEM_ORDER, listFoodItemOrder);
        mapOrderInCart.put("quantity", countQuantity(listFoodItemOrder));
        mapOrderInCart.put("totalMoney", getTotalMoney(listFoodItemOrder));

        session.setAttribute(MAP_ORDER_IN_CART, mapOrderInCart);
    }

    private void backToCart(Model model, Authentication authentication, HttpSession session) {

        Map<String, Object> mapFoodOrder = (Map<String, Object>) session.getAttribute(MAP_ORDER_IN_CART);
        if (mapFoodOrder != null) {
            CustomUser currentUser = (CustomUser) authentication.getPrincipal();
            UserAddress userAddress = userAddressService.getOneByUserId(currentUser.getId());
            model.addAttribute("userAddress", userAddress);
            model.addAttribute("format", new Format());
        }
        model.addAttribute("page", 8);
    }
}
