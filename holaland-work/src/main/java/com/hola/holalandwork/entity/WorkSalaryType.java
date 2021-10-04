package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkSalaryType {
    private int workSalaryTypeId;
    private String workSalaryTypeName;
    private boolean workSalaryTypeDeleted;
}
