package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestType;
import com.hola.holalandwork.repository.WorkRequestTypeRepository;
import com.hola.holalandwork.service.impl.WorkRequestTypeServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkRequestTypeServiceTest {
    @InjectMocks
    WorkRequestTypeServiceImpl workRequestTypeService;

    @Mock
    WorkRequestTypeRepository workRequestTypeRepository;

    public WorkRequestType genWorkRequestType() {
        WorkRequestType workRequestType = WorkRequestType.builder()
                .workRequestTypeId(1)
                .workRequestTypeIcon("icon.png")
                .workRequestTypeName("bán thời gian")
                .workRequestTypeCountRequestRecruitment(2)
                .workRequestTypeCountRequestFindJob(3)
                .build();
        return workRequestType;
    }

    @Test
    public void getAllWorkRequestType() throws Exception {
        List<WorkRequestType> workRequestTypeList = new ArrayList<>();
        workRequestTypeList.add(genWorkRequestType());
        when(workRequestTypeRepository.getAll()).thenReturn(workRequestTypeList);
        workRequestTypeService.getAll();
    }

    @Test
    public void getOneWorkRequestType() throws Exception {
        when(workRequestTypeRepository.getOne(1)).thenReturn(genWorkRequestType());
        workRequestTypeService.getOne(1);
    }
}
