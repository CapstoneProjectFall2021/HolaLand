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
    private double foodStoreOnlineRate;
    private int foodStoreOnlineMinPrice;
    private int foodStoreOnlineMaxPrice;
    private String foodStoreOnlineDescription;
    private int foodStoreOnlineCountRate;
    private boolean foodStoreOnlineDeleted;
}
