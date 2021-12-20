package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodReport;
import com.hola.holalandfood.repository.FoodReportRepository;
import com.hola.holalandfood.service.impl.FoodReportServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Transactional
public class FoodReportServiceTest {
    @InjectMocks
    FoodReportServiceImpl foodReportService;

    @Mock
    FoodReportRepository foodReportRepository;

    public FoodReport genFoodReport() {
        FoodReport foodReport = FoodReport.builder()
                .foodReportId(1)
                .userId(1)
                .foodStoreOnlineId(1)
                .foodOrderId(1)
                .foodReportContent("cơm dở quá")
                .foodReportCreateDate(Timestamp.valueOf("2021-09-01 09:01:15"))
                .foodReportUpdateDate(Timestamp.valueOf("2021-09-01 09:01:15"))
                .build();
        return foodReport;
    }

    @Test
    public void getAllFoodReport() {
        List<FoodReport> foodReportList = new ArrayList<>();
        foodReportList.add(genFoodReport());
        when(foodReportRepository.getAll()).thenReturn(foodReportList);
        foodReportService.getAll();
    }

    @Test
    public void getAllByOrderId() {
        List<FoodReport> foodReportList = new ArrayList<>();
        foodReportList.add(genFoodReport());
        when(foodReportRepository.getAllByOrderId(1)).thenReturn(foodReportList);
        foodReportService.getAllByOrderId(1);
    }

    @Test
    public void getOneFoodReport() {
        when(foodReportRepository.getOne(1)).thenReturn(genFoodReport());
        foodReportService.getOne(1);
    }

    @Test
    public void createFoodReport() {
        when(foodReportRepository.save(any())).thenReturn(true);
        foodReportService.save(genFoodReport());
    }

    @Test
    public void getUserReported() {
        when(foodReportRepository.getUserReported(1,1)).thenReturn(genFoodReport());
        foodReportService.getUserReported(1,1);
    }

    @Test
    public void deleteFoodReport() {
        when(foodReportRepository.delete(1)).thenReturn(true);
        foodReportService.delete(1);
    }



}
