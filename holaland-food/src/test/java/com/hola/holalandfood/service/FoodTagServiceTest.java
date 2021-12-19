package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.repository.FoodTagRepository;
import com.hola.holalandfood.service.impl.FoodTagServiceImpl;
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
public class FoodTagServiceTest {
    @InjectMocks
    FoodTagServiceImpl foodTagService;

    @Mock
    FoodTagRepository foodTagRepository;

    public FoodTag genFoodTag(){
        FoodTag foodTag = FoodTag.builder()
                .foodTagId(1)
                .foodTagName("cơm")
                .build();
        return foodTag;
    }

    @Test
    public void getAllFoodTag() {
        List<FoodTag> foodTagList = new ArrayList<>();
        foodTagList.add(genFoodTag());
        when(foodTagRepository.getAll()).thenReturn(foodTagList);
        foodTagService.getAll();
    }

    @Test
    public void getAllByStoreOnlineId() {
        List<FoodTag> foodTagList = new ArrayList<>();
        foodTagList.add(genFoodTag());
        when(foodTagRepository.getAllByStoreOnlineId(1)).thenReturn(foodTagList);
        foodTagService.getAllByStoreOnlineId(1);
    }

    @Test
    public void getOneFoodTag() {
        when(foodTagRepository.getOne(1)).thenReturn(genFoodTag());
        foodTagService.getOne(1);
    }

    @Test
    public void getAllByUserId() {
        List<FoodTag> foodTagList = new ArrayList<>();
        foodTagList.add(genFoodTag());
        when(foodTagRepository.getAllByUserId(1)).thenReturn(foodTagList);
        foodTagService.getAllByUserId(1);
    }

    @Test
    public void search() {
        List<FoodTag> foodTagList = new ArrayList<>();
        foodTagList.add(genFoodTag());
        when(foodTagRepository.search(any())).thenReturn(foodTagList);
        foodTagService.search("a");
    }
}
