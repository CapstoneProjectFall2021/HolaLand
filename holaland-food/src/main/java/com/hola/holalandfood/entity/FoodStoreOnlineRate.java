package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodStoreOnlineRate {

    private int foodRateId;
    private int foodStoreOnlineId;
    private int foodRatePoint;
    private String foodRateComment;
}
