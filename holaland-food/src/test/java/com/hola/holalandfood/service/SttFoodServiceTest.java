package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.SttFood;
import com.hola.holalandfood.repository.SttFoodRepository;
import com.hola.holalandfood.service.impl.SttFoodServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SttFoodServiceTest {
    @InjectMocks
    SttFoodServiceImpl sttFoodService;

    @Mock
    SttFoodRepository sttFoodRepository;

    public SttFood genSttFood(){
        SttFood sttFood = SttFood.builder()
                .sttFoodId(1)
                .sttFoodName("cơm ngon")
                .sttFoodCode(1)
                .sttFoodValue("reject")
                .sttFoodIcon("icon.jpg")
                .sttFoodCount(1)
                .build();
        return sttFood;
    }

    @Test
    public void getAllSttFood() {
        List<SttFood> sttFoodList = new ArrayList<>();
        sttFoodList.add(genSttFood());
        when(sttFoodRepository.getAll()).thenReturn(sttFoodList);
        sttFoodService.getAll();
    }

    @Test
    public void getAllHistoryOrder() {
        List<SttFood> sttFoodList = new ArrayList<>();
        sttFoodList.add(genSttFood());
        when(sttFoodRepository.getAllHistoryOrder()).thenReturn(sttFoodList);
        sttFoodService.getAllHistoryOrder();
    }

    @Test
    public void getOneFoodType() {
        when(sttFoodRepository.getOne(1)).thenReturn(genSttFood());
        sttFoodService.getOne(1);
    }
}
