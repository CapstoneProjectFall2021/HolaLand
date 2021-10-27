package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkRequestRecruitmentSaved {

    private int workRequestRecruitmentSavedId;
    private int userId;
    private int workRequestRecruitmentId;
    private boolean workRequestRecruitmentSavedDeleted;
}
