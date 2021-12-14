package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkComment;
import com.hola.holalandwork.repository.WorkCommentRepository;
import com.hola.holalandwork.service.impl.WorkCommentServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkCommentServiceTest {
    @InjectMocks
    WorkCommentServiceImpl workCommentService;

    @Mock
    WorkCommentRepository workCommentRepository;

    public WorkComment genWorkComment(){
        WorkComment workComment = WorkComment.builder()
                .workCommentId(1)
                .workRequestRecruitmentId(1)
                .userId(1)
                .workCommentContent("tin lừa đảo")
                .build();
        return workComment;
    }

    @Test
    public void getAllWorkComment() {
        List<WorkComment> workCommentList = new ArrayList<>();
        workCommentList.add(genWorkComment());
        when(workCommentRepository.getAll()).thenReturn(workCommentList);
        workCommentService.getAll();
    }
}
