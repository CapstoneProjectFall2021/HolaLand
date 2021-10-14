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
public class WorkSalaryType {

    private int workSalaryTypeId;
    private String workSalaryTypeName;
    private boolean workSalaryTypeDeleted;

}
