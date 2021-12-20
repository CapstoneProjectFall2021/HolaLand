package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkPaymentMethod;
import com.hola.holalandwork.repository.WorkPaymentMethodRepository;
import com.hola.holalandwork.service.impl.WorkPaymentMethodServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkPaymentMethodServiceTest {
    @InjectMocks
    WorkPaymentMethodServiceImpl workPaymentMethodService;

    @Mock
    WorkPaymentMethodRepository workPaymentMethodRepository;

    public WorkPaymentMethod genWorkPaymentMethod(){
        WorkPaymentMethod workPaymentMethod = WorkPaymentMethod.builder()
                .workPaymentMethodId(1)
                .workPaymentMethodName("theo tuần")
                .build();
        return workPaymentMethod;
    }

    @Test
    public void getAllWorkPaymentMethod() {
        List<WorkPaymentMethod> workPaymentMethodList = new ArrayList<>();
        workPaymentMethodList.add(genWorkPaymentMethod());
        when(workPaymentMethodRepository.getAll()).thenReturn(workPaymentMethodList);
        workPaymentMethodService.getAll();
    }

    @Test
    public void getOneWorkPaymentMethod() {
        when(workPaymentMethodRepository.getOne(1)).thenReturn(genWorkPaymentMethod());
        workPaymentMethodService.getOne(1);
    }
}
