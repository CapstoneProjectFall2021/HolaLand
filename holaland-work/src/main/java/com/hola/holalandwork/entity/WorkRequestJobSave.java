package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkRequestJobSave {

    private int workRequestJobSaveId;
    private int accountId;
    private int workRequestRecruitmentId;
    private boolean workRequestJobsSaveDeleted;

}
