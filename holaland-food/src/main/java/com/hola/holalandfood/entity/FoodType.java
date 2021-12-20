package com.hola.holalandfood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodType {

    private int foodTypeId;
    private String foodTypeIcon;
    private String foodTypeName;
    private int foodTypeCount;
    private boolean foodTypeDeleted;
}
