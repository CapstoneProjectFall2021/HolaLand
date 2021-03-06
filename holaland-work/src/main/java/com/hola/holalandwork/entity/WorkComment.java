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
public class WorkComment {

    private int workCommentId;
    private int workRequestRecruitmentId;
    private int userId;
    private String workCommentContent;
    private boolean workCommentDeleted;
}
