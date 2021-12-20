package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodType;
import com.hola.holalandfood.repository.FoodTypeRepository;
import com.hola.holalandfood.service.impl.FoodTypeServiceImpl;
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
public class FoodTypeServiceTest {
    @InjectMocks
    FoodTypeServiceImpl foodTypeService;

    @Mock
    FoodTypeRepository foodTypeRepository;

    public FoodType genFoodType(){
        FoodType foodType = FoodType.builder()
                .foodTypeId(1)
                .foodTypeIcon("icon.jpg")
                .foodTypeName("cơm")
                .foodTypeCount(3)
                .build();
        return foodType;
    }

    @Test
    public void getAllFoodType() {
        List<FoodType> foodTypeList = new ArrayList<>();
        foodTypeList.add(genFoodType());
        when(foodTypeRepository.getAll()).thenReturn(foodTypeList);
        foodTypeService.getAll();
    }

    @Test
    public void getOneFoodType() {
        when(foodTypeRepository.getOne(1)).thenReturn(genFoodType());
        foodTypeService.getOne(1);
    }
}
