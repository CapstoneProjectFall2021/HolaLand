package com.hola.holalandwork.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkComment {
    private int workCommentId;
    private int workRequestRecruitmentId;
    private int accountId;
    private String workCommentContent;
    private boolean workCommentDeleted;
}
