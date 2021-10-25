package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodOrder {

    private int foodOrderId;
    private int userId;
    private int foodStoreOnlineId;
    private int sttFoodCode;
    private int foodOrderTotalPrice;
    private String foodOrderCreatedDate;
    private String foodOrderNote;
    private boolean foodOrderDeleted;
}
