package com.hola.holalandtraffic.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class MotorbikeTaxiDrivers {

    private int tfMotorbikeTaxiDriversId;
    private String tfMotorbikeTaxiDriversName;
    private String tfMotorbikeTaxiDriversPhone;
    private String tfMotorbikeTaxiDriversImage;
    private String tfMotorbikeTaxiDriversLicensePlates;
    private String tfMotorbikeTaxiDriversType;
    private String tfMotorbikeTaxiDriversStartTime;
    private String tfMotorbikeTaxiDriversEndTime;
    private String tfMotorbikeTaxiDriversRating;
    private int tfMotorbikeTaxiDriversStatus;
    private boolean tfMotorbikeTaxiDriversDeleted;
}
