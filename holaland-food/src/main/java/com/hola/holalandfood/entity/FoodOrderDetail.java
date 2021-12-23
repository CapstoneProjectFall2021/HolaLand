package com.hola.holalandfood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodOrderDetail {

    private int foodOrderDetailId;
    private int foodOrderId;
    private int foodItemId;
    private String foodItemName;
    private double foodItemPrice;
    private int foodItemQuantity;
}
