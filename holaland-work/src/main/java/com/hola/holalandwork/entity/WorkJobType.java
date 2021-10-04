package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkJobType {
    private int workJobTypeId;
    private String workJobTypeName;
    private String workJobTypeIcon;
    private int workJobTypeCount;
    private boolean workJobTypeDeleted;
}
