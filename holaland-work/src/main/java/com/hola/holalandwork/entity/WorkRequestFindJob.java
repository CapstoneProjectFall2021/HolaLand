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
public class WorkRequestFindJob {

    private int workRequestFindJobId;
    private int userId;
    private int workJobTypeId;
    private int workTimeId;
    private int sttWorkCode;
    private int workSalaryUnitId;
    private String workRequestFindJobTitle;
    private long workRequestFindJobStartDateTime;
    private long workRequestFindJobEndDateTime;
    private long workRequestFindJobLastUpdateDateTime;
    private String workRequestFindJobDescription;
    private String workRequestFindJobPersonalExperience;
    private int workRequestFindJobExpectedSalary;
    private boolean workRequestFindJobDeleted;
}
