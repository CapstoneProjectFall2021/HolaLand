package com.hola.holalandweb.module.food.controller;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodItemCart;
import com.hola.holalandfood.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public FoodCartController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("")
    public String goToCart(Model model) {
        model.addAttribute("page", 8);
        return "module-food";
    }

    @GetMapping("/add")
    public ResponseEntity<?> addToCart(
            @RequestParam("foodId") int foodId,
            @RequestParam("storeId") int storeId,
            HttpSession session
    ) {
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

        listFoodOrder.forEach(s -> System.out.println(s));

        mapFoodOrder.put("listFoodOrder", listFoodOrder);
        mapFoodOrder.put("quantity", countQuantity(listFoodOrder));
        mapFoodOrder.put("totalMoney", getTotalMoney(listFoodOrder));

        session.setAttribute("mapFoodOrder", mapFoodOrder);
        return new ResponseEntity<>(HttpStatus.OK);
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
        return "module-food";
    }
}
