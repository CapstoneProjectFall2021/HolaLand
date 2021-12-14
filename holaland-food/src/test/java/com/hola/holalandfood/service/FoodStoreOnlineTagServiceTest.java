package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreOnlineTag;
import com.hola.holalandfood.repository.FoodStoreOnlineTagRepository;
import com.hola.holalandfood.service.impl.FoodStoreOnlineTagServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FoodStoreOnlineTagServiceTest {
    @InjectMocks
    FoodStoreOnlineTagServiceImpl foodStoreOnlineTagService;

    @Mock
    FoodStoreOnlineTagRepository foodStoreOnlineTagRepository;

    public FoodStoreOnlineTag genFoodStoreOnlineTag(){
        FoodStoreOnlineTag foodStoreOnlineTag = FoodStoreOnlineTag.builder()
                .foodStoreOnlineTagId(1)
                .foodStoreOnlineId(1)
                .foodTagId(1)
                .build();
        return foodStoreOnlineTag;
    }

    @Test
    public void getAllFoodStoreOnline() {
        List<FoodStoreOnlineTag> foodStoreOnlineTagList = new ArrayList<>();
        foodStoreOnlineTagList.add(genFoodStoreOnlineTag());
        when(foodStoreOnlineTagRepository.getAll()).thenReturn(foodStoreOnlineTagList);
        foodStoreOnlineTagService.getAll();
    }

    @Test
    public void getOneFoodStoreOnline() {
        when(foodStoreOnlineTagRepository.getOne(1)).thenReturn(genFoodStoreOnlineTag());
        foodStoreOnlineTagService.getOne(1);
    }

    @Test
    public void deleteAllTagByFoodStoreOnlineId() {
        when(foodStoreOnlineTagRepository.deleteAllTagByFoodStoreOnlineId(1)).thenReturn(true);
        foodStoreOnlineTagService.deleteAllTagByFoodStoreOnlineId(1);
    }


}
