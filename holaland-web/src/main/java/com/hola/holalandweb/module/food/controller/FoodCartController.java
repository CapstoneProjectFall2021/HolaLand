package com.hola.holalandweb.module.food.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.entity.UserAddress;
import com.hola.holalandcore.service.UserAddressService;
import com.hola.holalandcore.util.Format;
import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodItemCart;
import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.service.FoodItemService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/food/cart")
public class FoodCartController {

    private final FoodItemService foodItemService;
    private final UserAddressService userAddressService;
    private final FoodStoreOnlineService foodStoreOnlineService;

    @Autowired
    public FoodCartController(
            FoodItemService foodItemService,
            UserAddressService userAddressService,
            FoodStoreOnlineService foodStoreOnlineService
    ) {
        this.foodItemService = foodItemService;
        this.userAddressService = userAddressService;
        this.foodStoreOnlineService = foodStoreOnlineService;
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

        Map<String, Object> mapFoodOrder = (Map<String, Object>) session.getAttribute("mapFoodOrder");
        List<FoodItemCart> listFoodOrder;

        boolean flat = true;
        if (mapFoodOrder == null) {
            mapFoodOrder = new LinkedHashMap<>();
            listFoodOrder = new ArrayList<>();
            listFoodOrder.add(foodItemCart);
        } else {
            listFoodOrder = (List<FoodItemCart>) mapFoodOrder.get("listFoodOrder");
            if (listFoodOrder.get(0).getStoreId() != storeId) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
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
        }
        setAttributeCart(mapFoodOrder, listFoodOrder, session);
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

    @GetMapping("/delete")
    public String deleteCart(Model model, HttpSession session) {
        session.removeAttribute("mapFoodOrder");
        model.addAttribute("page", 8);
        return "redirect:" + "/food/cart";
    }

    @GetMapping("/delete/item")
    public String deleteItemInCart(
            @RequestParam("foodId") int foodId,
            HttpSession session
    ) {
        Map<String, Object> mapFoodOrder = (Map<String, Object>) session.getAttribute("mapFoodOrder");
        List<FoodItemCart> listFoodOrder = (List<FoodItemCart>) mapFoodOrder.get("listFoodOrder");

        for (int i = 0; i < listFoodOrder.size(); i++) {
            if (listFoodOrder.get(i).getFoodId() == foodId) {
                listFoodOrder.remove(i);
            }
        }

        if (listFoodOrder.size() == 0 || listFoodOrder.isEmpty()) {
            session.removeAttribute("mapFoodOrder");
        } else {
            setAttributeCart(mapFoodOrder, listFoodOrder, session);
        }
        return "redirect:" + "/food/cart";
    }

    @GetMapping("/update/quantity")
    public String updateQuantityItemInCart(
            @RequestParam("foodId") int foodId,
            @RequestParam("flag") int flag,
            HttpSession session
    ) {
        Map<String, Object> mapFoodOrder = (Map<String, Object>) session.getAttribute("mapFoodOrder");
        List<FoodItemCart> listFoodOrder = (List<FoodItemCart>) mapFoodOrder.get("listFoodOrder");

        for (int i = 0; i < listFoodOrder.size(); i++) {
            FoodItemCart foodItemCart = listFoodOrder.get(i);
            if (foodItemCart.getFoodId() == foodId) {
                if (flag == 1) {
                    foodItemCart.setQuantity(foodItemCart.getQuantity() + 1);
                } else {
                    foodItemCart.setQuantity(foodItemCart.getQuantity() - 1);

                    if (foodItemCart.getQuantity() == 0) {
                        listFoodOrder.remove(i);
                    }
                }
                foodItemCart.setTotalPrice(foodItemCart.getQuantity() * foodItemCart.getUnitPrice());
            }
        }

        if (listFoodOrder.size() == 0 || listFoodOrder.isEmpty()) {
            session.removeAttribute("mapFoodOrder");
        } else {
            setAttributeCart(mapFoodOrder, listFoodOrder, session);
        }
        return "redirect:" + "/food/cart";
    }

    private void setAttributeCart(Map<String, Object> mapFoodOrder, List<FoodItemCart> listFoodOrder, HttpSession session) {
        if (listFoodOrder != null || listFoodOrder.isEmpty()) {
            FoodStoreOnline foodStoreOnline = foodStoreOnlineService.getOne(listFoodOrder.get(0).getStoreId());
            mapFoodOrder.put("foodStoreOnlineId", foodStoreOnline.getFoodStoreOnlineId());
            mapFoodOrder.put("foodStoreOnlineName", foodStoreOnline.getFoodStoreOnlineName());
        }
        mapFoodOrder.put("listFoodOrder", listFoodOrder);
        mapFoodOrder.put("quantity", countQuantity(listFoodOrder));
        mapFoodOrder.put("totalMoney", getTotalMoney(listFoodOrder));

        session.setAttribute("mapFoodOrder", mapFoodOrder);
    }

    private void backToCart(Model model, Authentication authentication, HttpSession session) {

        Map<String, Object> mapFoodOrder = (Map<String, Object>) session.getAttribute("mapFoodOrder");
        if (mapFoodOrder != null) {
            CustomUser currentUser = (CustomUser) authentication.getPrincipal();
            UserAddress userAddress = userAddressService.getOneByUserId(currentUser.getId());
            model.addAttribute("userAddress", userAddress);
            model.addAttribute("format", new Format());
        }
        model.addAttribute("page", 8);
    }
}
