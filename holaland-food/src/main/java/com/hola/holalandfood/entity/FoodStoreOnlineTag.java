package com.hola.holalandfood.entity;

import lombok.*;

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
