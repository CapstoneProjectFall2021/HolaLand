package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkJobSave {

    private int workJobSaveId;
    private int userId;
    private int workRequestRecruitmentId;
    private boolean workJobsSaveDeleted;
}
