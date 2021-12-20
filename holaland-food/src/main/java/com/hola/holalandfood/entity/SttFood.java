package com.hola.holalandfood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
