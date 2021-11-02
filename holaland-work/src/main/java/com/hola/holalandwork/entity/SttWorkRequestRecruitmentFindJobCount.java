package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SttWorkRequestRecruitmentFindJobCount {

    private int sttWorkRequestRecruitmentFindJobCountId;
    private int userId;
    private int sttWorkRequestRecruitmentFindJobCountPending;
    private int sttWorkRequestRecruitmentFindJobCountDisclaimer;
    private int sttWorkRequestRecruitmentFindJobCountApproved;
    private int sttWorkRequestRecruitmentFindJobCountComplete;
    private int sttWorkRequestRecruitmentFindJobCountExpired;
    private int sttWorkRequestRecruitmentFindJobCountSaveDraft;
}
