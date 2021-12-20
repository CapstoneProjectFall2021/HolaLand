package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import com.hola.holalandfood.repository.FoodStoreOnlineRateRepository;
import com.hola.holalandfood.service.impl.FoodStoreOnlineRateServiceImpl;
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
public class FoodStoreOnlineRateServiceTest {
    @InjectMocks
    FoodStoreOnlineRateServiceImpl foodStoreOnlineRateService;

    @Mock
    FoodStoreOnlineRateRepository foodStoreOnlineRateRepository;

    public FoodStoreOnlineRate genFoodStoreOnlineRate(){
        FoodStoreOnlineRate foodStoreOnlineRate = FoodStoreOnlineRate.builder()
                .foodStoreOnlineRateId(1)
                .userId(1)
                .foodStoreOnlineId(1)
                .foodStoreOnlineRatePoint(4)
                .foodStoreOnlineRateComment("đồ ăn ngon")
                .foodStoreOnlineRateCreateTime(Timestamp.valueOf("2021-09-01 09:01:15"))
                .foodStoreOnlineRateUpdateTime(Timestamp.valueOf("2021-09-01 09:01:15"))
                .build();
        return foodStoreOnlineRate;
    }

    @Test
    public void getAllFoodStoreRate() {
        List<FoodStoreOnlineRate> foodStoreOnlineRateList = new ArrayList<>();
        foodStoreOnlineRateList.add(genFoodStoreOnlineRate());
        when(foodStoreOnlineRateRepository.getAll()).thenReturn(foodStoreOnlineRateList);
        foodStoreOnlineRateService.getAll();
    }

    @Test
    public void getAllCommentByStoreOnlineId() {
        List<FoodStoreOnlineRate> foodStoreOnlineRateList = new ArrayList<>();
        foodStoreOnlineRateList.add(genFoodStoreOnlineRate());
        when(foodStoreOnlineRateRepository.getAllCommentByStoreOnlineId(1)).thenReturn(foodStoreOnlineRateList);
        foodStoreOnlineRateService.getAllCommentByStoreOnlineId(1);
    }

    @Test
    public void getOneFoodStoreRate() {
        when(foodStoreOnlineRateRepository.getOne(1)).thenReturn(genFoodStoreOnlineRate());
        foodStoreOnlineRateService.getOne(1);
    }

    @Test
    public void checkUserCommentExist() {
        when(foodStoreOnlineRateRepository.getUserComment(1,1)).thenReturn(genFoodStoreOnlineRate());
        foodStoreOnlineRateService.getUserComment(1,1);
    }

    @Test
    public void getUserComment() {
        when(foodStoreOnlineRateRepository.checkUserCommentExist(1,1)).thenReturn(true);
        foodStoreOnlineRateService.checkUserCommentExist(1,1);
    }

    @Test
    public void createFoodStoreRate() {
        when(foodStoreOnlineRateRepository.save(any())).thenReturn(true);
        foodStoreOnlineRateService.save(genFoodStoreOnlineRate());
    }

    @Test
    public void updateFoodStoreRate() {
        when(foodStoreOnlineRateRepository.update(any())).thenReturn(true);
        foodStoreOnlineRateService.update(genFoodStoreOnlineRate());
    }


}
