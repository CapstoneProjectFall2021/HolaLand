package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkReportReason;
import com.hola.holalandwork.repository.WorkReportReasonRepository;
import com.hola.holalandwork.service.impl.WorkReportReasonServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkReportReasonServiceTest {
    @InjectMocks
    WorkReportReasonServiceImpl workReportReasonService;

    @Mock
    WorkReportReasonRepository workReportReasonRepository;

    public WorkReportReason genWorkReportReason(){
        WorkReportReason workReportReason = WorkReportReason.builder()
                .workReportReasonId(1)
                .workReportReasonName("lừa đảo")
                .build();
        return workReportReason;
    }

    @Test
    public void getAllWorkReportReason() {
        List<WorkReportReason> workReportReasonList = new ArrayList<>();
        workReportReasonList.add(genWorkReportReason());
        when(workReportReasonRepository.getAll()).thenReturn(workReportReasonList);
        workReportReasonService.getAll();
    }

    @Test
    public void getOneWorkReportReason() {
        when(workReportReasonRepository.getOne(1)).thenReturn(genWorkReportReason());
        workReportReasonService.getOne(1);
    }
}
