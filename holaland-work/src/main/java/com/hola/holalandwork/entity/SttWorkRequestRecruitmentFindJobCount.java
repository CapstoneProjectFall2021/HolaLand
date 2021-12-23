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
public class SttWorkRequestRecruitmentFindJobCount {

    private int sttWorkRequestRecruitmentFindJobCountId;
    private int userId;
    private int sttWorkRequestRecruitmentFindJobCountPending;
    private int sttWorkRequestRecruitmentFindJobCountReject;
    private int sttWorkRequestRecruitmentFindJobCountApproved;
    private int sttWorkRequestRecruitmentFindJobCountComplete;
    private int sttWorkRequestRecruitmentFindJobCountExpired;
    private int sttWorkRequestRecruitmentFindJobCountSaveDraft;
}
