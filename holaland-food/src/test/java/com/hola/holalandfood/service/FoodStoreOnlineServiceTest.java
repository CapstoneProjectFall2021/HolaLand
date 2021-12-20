package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.repository.FoodStoreOnlineRepository;
import com.hola.holalandfood.service.impl.FoodStoreOnlineServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Transactional
public class FoodStoreOnlineServiceTest {
    @InjectMocks
    FoodStoreOnlineServiceImpl foodStoreOnlineService;

    @Mock
    FoodStoreOnlineRepository foodStoreOnlineRepository;

    public FoodStoreOnline genFoodStoreOnline() {
        FoodStoreOnline foodStoreOnline = FoodStoreOnline.builder()
                .foodStoreOnlineId(1)
                .userId(1)
                .sttFoodCode(1)
                .foodStoreOnlineImage("image.jpg")
                .foodStoreOnlineName("quán cơm con gà")
                .foodStoreOnlineRate(4)
                .foodStoreOnlineMinPrice(15000)
                .foodStoreOnlineMaxPrice(30000)
                .foodStoreOnlineDescription("chuyên bán gà sạch")
                .foodStoreOnlineCountFoodItem(45)
                .foodStoreOnlineCountRate(56)
                .foodStoreOnlineCountReport(21)
                .foodStoreOnlinePauseSellingFlag(true)
                .foodStoreOnlineStopSellingFlag(false)
                .build();
        return foodStoreOnline;
    }

    @Test
    public void getAllFoodStoreOnline() {
        List<FoodStoreOnline> foodStoreOnlineList = new ArrayList<>();
        foodStoreOnlineList.add(genFoodStoreOnline());
        when(foodStoreOnlineRepository.getAll()).thenReturn(foodStoreOnlineList);
        foodStoreOnlineService.getAll();
    }

    @Test
    public void getAllByType() {
        List<FoodStoreOnline> foodStoreOnlineList = new ArrayList<>();
        foodStoreOnlineList.add(genFoodStoreOnline());
        when(foodStoreOnlineRepository.getAllByType(1,1)).thenReturn(foodStoreOnlineList);
        foodStoreOnlineService.getAllByType(1,1);
    }

    @Test
    public void getOneFoodStoreOnline() {
        when(foodStoreOnlineRepository.getOne(1)).thenReturn(genFoodStoreOnline());
        foodStoreOnlineService.getOne(1);
    }

    @Test
    public void getOneByUserId() {
        when(foodStoreOnlineRepository.getOneByUserId(1)).thenReturn(genFoodStoreOnline());
        foodStoreOnlineService.getOneByUserId(1);
    }

    @Test
    public void getOneByOrderId() {
        when(foodStoreOnlineRepository.getOneByOrderId(1)).thenReturn(genFoodStoreOnline());
        foodStoreOnlineService.getOneByOrderId(1);
    }

    @Test
    public void checkUserIsOwner() {
        when(foodStoreOnlineRepository.checkUserIsOwner(1,1)).thenReturn(true);
        foodStoreOnlineService.checkUserIsOwner(1,1);
    }

    @Test
    public void updateShopInfo() {
        when(foodStoreOnlineRepository.updateShopInfo(any())).thenReturn(true);
        foodStoreOnlineService.updateShopInfo(genFoodStoreOnline());
    }
}
