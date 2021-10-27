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
public class WorkRequestType {

    private int workRequestTypeId;
    private String workRequestTypeIcon;
    private String workRequestTypeName;
    private int workRequestTypeCountRequestRecruitment;
    private int workRequestTypeCountRequestFindJob;
    private boolean workRequestTypeDeleted;
}
