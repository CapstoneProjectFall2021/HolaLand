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
public class WorkRequestBook {

    private int workRequestBookId;
    private int accountId;
    private int workRequestFindJobId;
    private int workRequestBookSttId;
    private boolean workRequestBookDeleted;
}
