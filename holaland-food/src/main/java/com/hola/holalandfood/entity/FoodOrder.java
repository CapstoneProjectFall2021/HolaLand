package com.hola.holalandfood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private double foodOrderTotalPrice;
    private Timestamp foodOrderCreatedDate;
    private String foodOrderNote;
    private String foodOrderReasonReject;
    private boolean foodOrderDeleted;
}
