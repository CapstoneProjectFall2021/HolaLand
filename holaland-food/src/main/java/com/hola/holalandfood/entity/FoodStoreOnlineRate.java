package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodStoreOnlineRate {

    private int foodStoreOnlineRateId;
    private int foodStoreOnlineId;
    private int foodStoreOnlineRatePoint;
    private String foodStoreOnlineRateComment;
}
