package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodTag {

    private int foodTagId;
    private String foodTagName;
}
