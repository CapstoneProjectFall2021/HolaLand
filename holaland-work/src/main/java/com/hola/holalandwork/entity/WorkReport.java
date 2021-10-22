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
public class WorkReport {

    private int workReportId;
    private int sttWorkReportId;
    private int workReportReasonId;
    private String workReportDescription;
    private boolean workReportDeleted;
}
