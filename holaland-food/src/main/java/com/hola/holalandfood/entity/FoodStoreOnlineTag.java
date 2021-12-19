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
public class FoodStoreOnlineTag {

    private int foodStoreOnlineTagId;
    private int foodStoreOnlineId;
    private int foodTagId;
}
