package com.hola.holalandfood.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class SttFood {

    private int sttFoodId;
    private String sttFoodName;
    private int sttFoodCode;
    private String sttFoodValue;
    private String sttFoodIcon;
    private int sttFoodCount;
}
