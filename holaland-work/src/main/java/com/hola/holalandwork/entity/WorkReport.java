package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkReport {
    private int workReportId;
    private String workReportReason;
    private String workReportDescription;
    private String workReportStatus;
}
