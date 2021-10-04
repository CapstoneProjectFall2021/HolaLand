package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkRequestFindJob {
    private int workRequestFindJobId;
    private int accountId;
    private int workJobTypeId;
    private int workTimeId;
    private int sttWorkRequestFindJobId;
    private String workRequestFindJobTitle;
    private String workRequestFindJobStartDateTime;
    private String workRequestFindJobEndDateTime;
    private String workRequestFindJobLastUpdateDateTime;
    private String workRequestFindJobDescription;
    private String workRequestFindJobPersonalExperience;
    private boolean workRequestFindJobDeleted;
}
