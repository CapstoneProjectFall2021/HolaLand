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

public class FoodReport {

    private int foodReportId;
    private int userId;
    private int foodStoreOnlineId;
    private int foodOrderId;
    private String foodReportContent;
    private Timestamp foodReportCreateDate;
    private Timestamp foodReportUpdateDate;
    private boolean foodReportDeleted;
}
