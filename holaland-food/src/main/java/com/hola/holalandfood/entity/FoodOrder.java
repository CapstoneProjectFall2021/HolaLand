package com.hola.holalandfood.entity;

import lombok.*;

import java.sql.Timestamp;

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
    private Timestamp foodOrderCreatedDate;
    private String foodOrderNote;
    private String foodOrderReasonReject;
    private boolean foodOrderDeleted;
}
