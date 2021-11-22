package com.hola.holalandfood.entity;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

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
    private int userId;
    private Timestamp foodStoreOnlineRateCreateTime;
    private Timestamp foodStoreOnlineRateUpdateTime;
    private boolean foodStoreonlineDelete;
}
