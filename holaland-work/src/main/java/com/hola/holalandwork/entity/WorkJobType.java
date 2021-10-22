package com.hola.holalandwork.entity;

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
public class WorkJobType {

    private int workJobTypeId;
    private String workJobTypeIcon;
    private String workJobTypeName;
    private int workJobTypeCountRequestRecruitment;
    private int workJobTypeCountRequestFindJob;
    private boolean workJobTypeDeleted;
}
