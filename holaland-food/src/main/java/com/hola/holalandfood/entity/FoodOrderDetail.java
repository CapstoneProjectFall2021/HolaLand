package com.hola.holalandfood.entity;

import lombok.*;

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
    private int foodItemPrice;
    private int foodItemQuantity;
}
