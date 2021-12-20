package com.hola.holalandfood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class FoodItem {

    private int foodItemId;
    private int foodStoreOnlineId;
    private int foodTagId;
    private int foodTypeId;
    private String foodItemImage;
    private String foodItemName;
    private int foodItemPrice;
    private int foodItemSoldNumber;
    private int foodItemIsActive;
    private int foodItemDeleted;
}
