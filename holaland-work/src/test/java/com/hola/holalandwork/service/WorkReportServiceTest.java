package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkReport;
import com.hola.holalandwork.repository.WorkReportRepository;
import com.hola.holalandwork.service.impl.WorkReportServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkReportServiceTest {
    @InjectMocks
    WorkReportServiceImpl workReportService;

    @Mock
    WorkReportRepository workReportRepository;

    public WorkReport genWorkReport(){
        WorkReport workReport = WorkReport.builder()
                .workReportId(1)
                .sttWorkCode(1)
                .workReportReasonId(1)
                .workReportDescription("lừa đảo")
                .build();
        return workReport;
    }

    @Test
    public void getAllWorkReport() {
        List<WorkReport> workReportList = new ArrayList<>();
        workReportList.add(genWorkReport());
        when(workReportRepository.getAll()).thenReturn(workReportList);
        workReportService.getAll();
    }
}
