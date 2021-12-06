package com.hola.holalandfood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class FoodItemCart implements Serializable {

    private int foodId;
    private int storeId;
    private String foodName;
    private String foodImage;
    private double unitPrice;
    private int quantity;
    private double totalPrice;
}
