package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreOnlineType;
import com.hola.holalandfood.repository.FoodStoreOnlineTypeRepository;
import com.hola.holalandfood.service.impl.FoodStoreOnlineTypeServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Transactional
public class FoodStoreOnlineTypeServiceTest {
    @InjectMocks
    FoodStoreOnlineTypeServiceImpl foodStoreOnlineTypeService;

    @Mock
    FoodStoreOnlineTypeRepository foodStoreOnlineTypeRepository;

    public FoodStoreOnlineType genFoodStoreOnlineType(){
        FoodStoreOnlineType foodStoreOnlineType = FoodStoreOnlineType.builder()
                .foodStoreOnlineTypeId(1)
                .foodStoreOnlineId(1)
                .foodTypeId(1)
                .build();
        return foodStoreOnlineType;
    }

    @Test
    public void getAllFoodStoreTypeOnline() {
        List<FoodStoreOnlineType> foodStoreOnlineTypeList = new ArrayList<>();
        foodStoreOnlineTypeList.add(genFoodStoreOnlineType());
        when(foodStoreOnlineTypeRepository.getAll()).thenReturn(foodStoreOnlineTypeList);
        foodStoreOnlineTypeService.getAll();
    }

    @Test
    public void getOneFoodStoreTypeOnline() {
        when(foodStoreOnlineTypeRepository.getOne(1)).thenReturn(genFoodStoreOnlineType());
        foodStoreOnlineTypeService.getOne(1);
    }
}
