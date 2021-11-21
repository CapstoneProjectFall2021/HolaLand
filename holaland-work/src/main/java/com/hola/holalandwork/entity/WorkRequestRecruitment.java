package com.hola.holalandwork.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkRequestRecruitment {

    private int workRequestRecruitmentId;
    private int userId;
    private int workPaymentMethodId;
    private int workRequestTypeId;
    private int sttWorkCode;
    private int workSalaryUnitId;
    private String workRequestRecruitmentTitle;
    private Date workRequestRecruitmentStartDateTime;
    private Date workRequestRecruitmentEndDateTime;
    private Date workRequestRecruitmentLastUpdateDateTime;
    private String workRequestRecruitmentDescription;
    private String workRequestRecruitmentRequirement;
    private String workRequestRecruitmentBenefit;
    private String workRequestRecruitmentSalary;
    private int workRequestRecruitmentQuantity;
    private boolean workRequestRecruitmentExperienceRequirement;
    private boolean workRequestRecruitmentGenderRequirement;
    private String workRequestRecruitmentWorkLocation;
    private String workRequestRecruitmentNote;
    private boolean workRequestRecruitmentDeleted;
}
