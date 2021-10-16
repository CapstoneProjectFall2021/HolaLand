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
public class WorkRequestApply {

    private int workRequestApplyId;
    private int accountId;
    private int workRequestRecruitmentId;
    private int sttWorkRequestApplyId;
    private boolean workRequestApplyDeleted;
}
