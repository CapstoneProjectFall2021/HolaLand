package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodStoreOnline {

    private int foodStoreOnlineId;
    private int userId;
    private int foodStoreTypeId;
    private int sttFoodCode;
    private String foodStoreOnlineImage;
    private String foodStoreOnlineName;
    private double foodStoreRate;
    private int foodStoreMinPrice;
    private int foodStoreMaxPrice;
    private String foodStoreDescription;
    private boolean foodStoreOnlineDeleted;
}
