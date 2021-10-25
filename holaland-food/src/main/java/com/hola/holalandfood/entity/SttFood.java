package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SttFood {

    private int sttFoodId;
    private String sttFoodName;
    private int sttFoodCode;
    private String sttFoodValue;
    private String sttFoodIcon;
    private int sttFoodCount;
}
