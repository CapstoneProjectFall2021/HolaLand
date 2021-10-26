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
public class WorkRequestRecruitment {

    private int workRequestRecruitmentId;
    private int userId;
    private int workPaymentMethodId;
    private int workRequestTypeId;
    private int sttWorkCode;
    private int workSalaryUnitId;
    private String workRequestRecruitmentTitle;
    private long workRequestRecruitmentStartDateTime;
    private long workRequestRecruitmentEndDateTime;
    private long workRequestRecruitmentLastUpdateDateTime;
    private String workRequestRecruitmentDescription;
    private String workRequestRecruitmentRequirement;
    private String workRequestRecruitmentBenefit;
    private String workRequestRecruitmentSalary;
    private int workRequestRecruitmentQuantity;
    private String workRequestRecruitmentExperienceRequirement;
    private String workRequestRecruitmentGenderRequirement;
    private String workRequestRecruitmentWorkLocation;
    private boolean workRequestRecruitmentDeleted;
}
