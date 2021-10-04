package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkRequestRecruitment {
    private int workRequestRecruitmentId;
    private int accountId;
    private int workSalaryTypeId;
    private int workJobTypeId;
    private int sttWorkRequestRecruitmentId;
    private String workRequestRecruitmentTitle;
    private String workRequestRecruitmentStartDateTime;
    private String workRequestRecruitmentEndDateTime;
    private String workRequestRecruitmentLastUpdateDateTime;
    private String workRequestRecruitmentDescription;
    private String workRequestRecruitmentRequirement;
    private String workRequestRecruitmentBenefit;
    private String workRequestRecruitmentWage;
    private int workRequestRecruitmentQuantity;
    private String workRequestRecruitmentExperienceRequirement;
    private String workRequestRecruitmentGenderRequirement;
    private String workRequestRecruitmentWorkLocation;
    private boolean workRequestRecruitmentDeleted;
}
