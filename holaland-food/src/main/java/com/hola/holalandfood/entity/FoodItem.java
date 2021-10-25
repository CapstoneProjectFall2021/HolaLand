package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodItem {

    private int foodItemId;
    private int foodStoreOnlineId;
    private int foodTagId;
    private int foodTypeId;
    private String foodItemImage;
    private String foodItemName;
    private int foodItemPrice;
    private int foodSoldNumber;
    private boolean foodIsActive;
    private boolean foodItemDeleted;
}
