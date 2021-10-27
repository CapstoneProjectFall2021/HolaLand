package com.hola.holalandfood.entity;

import lombok.*;

import java.sql.Date;

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
    private Date foodOrderCreatedDate;
    private String foodOrderNote;
    private boolean foodOrderDeleted;
}
