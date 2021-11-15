package com.hola.holalandfood.entity;

import lombok.*;

import java.sql.Date;

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
    private Date foodStoreOnlineRateCreateTime;
    private Date foodStoreOnlineRateUpdateTime;
}
