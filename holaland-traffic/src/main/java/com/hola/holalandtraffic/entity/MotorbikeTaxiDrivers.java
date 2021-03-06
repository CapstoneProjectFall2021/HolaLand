package com.hola.holalandtraffic.entity;

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
public class MotorbikeTaxiDrivers {

    private int tfMotorbikeTaxiDriversId;
    private String tfMotorbikeTaxiDriversName;
    private boolean tfMotorbikeTaxiDriversGender;
    private String tfMotorbikeTaxiDriversPhone;
    private String tfMotorbikeTaxiDriversImage;
    private String tfMotorbikeTaxiDriversLicensePlates;
    private String tfMotorbikeTaxiDriversVehicleType;
    private String tfMotorbikeTaxiDriversStartTime;
    private String tfMotorbikeTaxiDriversEndTime;
    private double tfMotorbikeTaxiDriversRating;
    private int tfMotorbikeTaxiDriversStatus;
    private boolean tfMotorbikeTaxiDriversDeleted;
}
