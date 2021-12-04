package com.hola.holalandweb.module.food.controller;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodItemCart;
import com.hola.holalandfood.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food/order")
public class FoodOrderController {

    private final FoodItemService foodItemService;

    @Autowired
    public FoodOrderController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("add-to-cart")
    public ResponseEntity<?> getCourseById(
            @RequestParam("foodId") int foodId,
            @RequestParam("storeId") int storeId,
            HttpSession session
    ) {
        FoodItem foodItem = foodItemService.getOne(foodId);
        FoodItemCart foodItemCart = FoodItemCart.builder()
                .foodId(foodId)
                .foodName(foodItem.getFoodItemName())
                .unitPrice(foodItem.getFoodItemPrice())
                .quantity(1)
                .totalPrice(foodItem.getFoodItemPrice())
                .build();

        List<FoodItemCart> listFoodOrder = null;
        listFoodOrder = (ArrayList<FoodItemCart>) session.getAttribute("listFoodOrder");

        boolean flat = true;
        if (listFoodOrder == null) {
            listFoodOrder = new ArrayList<>();
            listFoodOrder.add(foodItemCart);
            session.setAttribute("listFoodOrder", listFoodOrder);
        } else {
            for (FoodItemCart f : listFoodOrder) {
                if (f.getFoodId() == foodId) {
                    f.setQuantity(f.getQuantity() + 1);
                    f.setTotalPrice(f.getQuantity() * f.getUnitPrice());
                    flat = false;
                }
            }
            if (flat) {
                listFoodOrder.add(foodItemCart);
                flat = true;
            }
            session.setAttribute("listFoodOrder", listFoodOrder);
        }

        listFoodOrder.forEach(s -> System.out.println(s));

        return new ResponseEntity<>(listFoodOrder.size(), HttpStatus.OK);
    }
}
