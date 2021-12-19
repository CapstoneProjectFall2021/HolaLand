package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.repository.FoodItemRepository;
import com.hola.holalandfood.service.impl.FoodItemServiceImpl;
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

    @Test
    public void getAllFoodItem() throws Exception {
        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(genFoodItem());
        when(foodItemRepository.getAll()).thenReturn(foodItemList);
        foodItemService.getAll();
    }

    @Test
    public void getAllByStoreOnlineId() throws Exception {
        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(genFoodItem());
        when(foodItemRepository.getAllByStoreOnlineId(1)).thenReturn(foodItemList);
        foodItemService.getAllByStoreOnlineId(1);
    }

    @Test
    public void getAllByStoreOnlineIdAndTagId() throws Exception {
        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(genFoodItem());
        when(foodItemRepository.getAllByStoreOnlineIdAndTagId(1,1)).thenReturn(foodItemList);
        foodItemService.getAllByStoreOnlineIdAndTagId(1,1);
    }

    @Test
    public void getOneFoodItem() throws Exception {
        when(foodItemRepository.getOne(1)).thenReturn(genFoodItem());
        foodItemService.getOne(1);
    }

    @Test
    public void getAllByUserId() throws Exception {
        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(genFoodItem());
        when(foodItemRepository.getAllByUserId(1)).thenReturn(foodItemList);
        foodItemService.getAllByUserId(1);
    }

    @Test
    public void deletedOneFoodItem() throws Exception {
        when(foodItemRepository.deletedOne(any())).thenReturn(true);
        foodItemService.deletedOne(genFoodItem());
    }

    @Test
    public void updateFoodItem() throws Exception {
        when(foodItemRepository.update(any())).thenReturn(true);
        foodItemService.update(genFoodItem());
    }

    @Test
    public void searchFood() throws Exception {
        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(genFoodItem());
        when(foodItemRepository.search(any())).thenReturn(foodItemList);
        foodItemService.search("b");
    }








}
