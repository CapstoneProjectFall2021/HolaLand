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

@Controller
@RequestMapping("/food/order")
public class FoodOrderController {

    private final FoodItemService foodItemService;

    @Autowired
    public FoodOrderController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("add-to-cart")
    public ResponseEntity<?> getCourseById(@RequestParam("foodId") int foodId, @RequestParam("storeId") int storeId) {
        System.out.println("\n\n\n" + foodId + " - " + storeId + "\n\n\n");

        FoodItem foodItem = foodItemService.getOne(foodId);
        FoodItemCart foodItemCart = FoodItemCart.builder()
                .foodId(foodId)
                .foodName(foodItem.getFoodItemName())
                .build();

        if(true) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
