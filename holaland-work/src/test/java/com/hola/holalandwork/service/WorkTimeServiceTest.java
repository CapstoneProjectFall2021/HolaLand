package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkSalaryUnit;
import com.hola.holalandwork.entity.WorkTime;
import com.hola.holalandwork.repository.WorkTimeRepository;
import com.hola.holalandwork.service.impl.WorkTimeServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkTimeServiceTest {
    @InjectMocks
    WorkTimeServiceImpl workTimeService;

    @Mock
    WorkTimeRepository workTimeRepository;

    public WorkTime genWorkTime(){
        WorkTime workTime = WorkTime.builder()
                .workTimeId(1)
                .workTimeName("buổi sáng")
                .build();
        return workTime;
    }

    @Test
    public void getAllWorkTime() throws Exception {
        List<WorkTime> workTimeList = new ArrayList<>();
        workTimeList.add(genWorkTime());
        when(workTimeRepository.getAll()).thenReturn(workTimeList);
        workTimeService.getAll();
    }

    @Test
    public void getOneWorkTime() throws Exception {
        when(workTimeRepository.getOne(1)).thenReturn(genWorkTime());
        workTimeService.getOne(1);
    }
}
