package com.hola.holalandwork.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class WorkRequestFindJob {

    private int workRequestFindJobId;
    private int userId;
    private int workRequestTypeId;
    private int workTimeId;
    private int sttWorkCode;
    private int workPaymentMethodId;
    private int workSalaryUnitId;
    private String workRequestFindJobTitle;

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date workRequestFindJobStartDateTime;

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date workRequestFindJobEndDateTime;

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date workRequestFindJobLastUpdateDateTime;

    private String workRequestFindJobDescription;
    private String workRequestFindJobPersonalExperience;
    private String workRequestFindJobExpectedLocation;
    private int workRequestFindJobExpectedSalary;
    private boolean workRequestFindJobDeleted;
}
