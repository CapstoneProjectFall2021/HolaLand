package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.repository.FoodItemRepository;
import com.hola.holalandfood.service.impl.FoodItemServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FoodItemServiceTest {

    @InjectMocks
    FoodItemServiceImpl foodItemService;

    @Mock
    FoodItemRepository foodItemRepository;

    public FoodItem genFoodItem(){
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodItemId(1);
        foodItem.setFoodStoreOnlineId(1);
        foodItem.setFoodTagId(2);
        foodItem.setFoodTypeId(1);
        foodItem.setFoodItemImage("image.png");
        foodItem.setFoodItemName("Pho");
        foodItem.setFoodItemPrice(15000);
        foodItem.setFoodItemSoldNumber(2);
        foodItem.setFoodItemIsActive(1);
        foodItem.setFoodItemDeleted(0);
        return foodItem;
    }

    @Test
    public void createFoodItem() throws Exception {
        when(foodItemRepository.save(any())).thenReturn(true);
        foodItemService.save(genFoodItem());
    }
}
