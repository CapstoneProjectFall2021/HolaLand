package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkSalaryUnit;
import com.hola.holalandwork.repository.WorkSalaryUnitRepository;
import com.hola.holalandwork.service.impl.WorkSalaryUnitServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkSalaryUnitServiceTest {
    @InjectMocks
    WorkSalaryUnitServiceImpl workSalaryUnitService;

    @Mock
    WorkSalaryUnitRepository workSalaryUnitRepository;

    public WorkSalaryUnit genWorkSalaryUnit(){
        WorkSalaryUnit workSalaryUnit = WorkSalaryUnit.builder()
                .workSalaryUnitId(1)
                .workSalaryUnitName("VND")
                .build();
        return workSalaryUnit;
    }

    @Test
    public void getAllWorkSalaryUnit() throws Exception {
        List<WorkSalaryUnit> workSalaryUnitList = new ArrayList<>();
        workSalaryUnitList.add(genWorkSalaryUnit());
        when(workSalaryUnitRepository.getAll()).thenReturn(workSalaryUnitList);
        workSalaryUnitService.getAll();
    }

    @Test
    public void getOneWorkRequestType() throws Exception {
        when(workSalaryUnitRepository.getOne(1)).thenReturn(genWorkSalaryUnit());
        workSalaryUnitService.getOne(1);
    }
}
