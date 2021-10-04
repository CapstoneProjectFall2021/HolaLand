package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkRequestApply {
    private int workRequestApplyId;
    private int accountId;
    private int workRequestRecruitmentId;
    private int workRequestApplySttId;
    private int workRequestApplyDeleted;
}
