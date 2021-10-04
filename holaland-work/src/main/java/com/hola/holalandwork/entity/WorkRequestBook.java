package com.hola.holalandwork.entity;

import lombok.*;

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
