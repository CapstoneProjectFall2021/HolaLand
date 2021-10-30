package com.hola.holalandtraffic.entity;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bus {
//
    private int tfBusId;
    private String tfBusName;
    private String tfBusStartTime;
    private String tfBusEndTime;
    private String tfBusInfo;
    private List<String> tfBusStops;
    private int tfBusPrice;
    private int tfBusStatus;
    private boolean tfBusDeleted;
}
