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
public class FoodStoreOnlineRate {

    private int foodStoreOnlineRateId;
    private int userId;
    private int foodStoreOnlineId;
    private int foodStoreOnlineRatePoint;
    private String foodStoreOnlineRateComment;
    private Timestamp foodStoreOnlineRateCreateTime;
    private Timestamp foodStoreOnlineRateUpdateTime;
    private boolean foodStoreOnlineDeleted;
}
