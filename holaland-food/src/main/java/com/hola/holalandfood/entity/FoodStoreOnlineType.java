package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodStoreOnlineType {

    private int foodStoreOnlineTypeId;
    private int foodStoreOnlineId;
    private int foodTypeId;
}
