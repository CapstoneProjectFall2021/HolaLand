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
    private int accountId;
    private int workJobTypeId;
    private int workTimeId;
    private int sttWorkRequestFindJobId;
    private int workSalaryUnitId;
    private String workRequestFindJobTitle;
    private String workRequestFindJobStartDateTime;
    private String workRequestFindJobEndDateTime;
    private String workRequestFindJobLastUpdateDateTime;
    private String workRequestFindJobDescription;
    private String workRequestFindJobPersonalExperience;
    private boolean workRequestFindJobDeleted;
}
