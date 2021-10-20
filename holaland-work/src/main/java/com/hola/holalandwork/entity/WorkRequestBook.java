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
    private int userId;
    private int workRequestFindJobId;
    private int sttWorkRequestBookId;
    private boolean workRequestBookDeleted;
}
