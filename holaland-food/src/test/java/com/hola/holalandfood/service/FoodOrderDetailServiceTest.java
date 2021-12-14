package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.repository.FoodOrderDetailRepository;
import com.hola.holalandfood.service.impl.FoodOrderDetailServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FoodOrderDetailServiceTest {
    @InjectMocks
    FoodOrderDetailServiceImpl foodOrderDetailService;

    @Mock
    FoodOrderDetailRepository foodOrderDetailRepository;

    public FoodOrderDetail genFoodOrderDetail(){
        FoodOrderDetail foodOrderDetail = FoodOrderDetail.builder()
                .foodOrderDetailId(1)
                .foodOrderId(1)
                .foodItemId(1)
                .foodItemName("bánh mỳ trứng")
                .foodItemPrice(15000)
                .foodItemQuantity(1)
                .build();
        return foodOrderDetail;
    }

    @Test
    public void getAllFoodOrderDetail() throws Exception {
        List<FoodOrderDetail> foodOrderDetailList = new ArrayList<>();
        foodOrderDetailList.add(genFoodOrderDetail());
        when(foodOrderDetailRepository.getAll()).thenReturn(foodOrderDetailList);
        foodOrderDetailService.getAll();
    }

    @Test
    public void getAllByOrderId() throws Exception {
        List<FoodOrderDetail> foodOrderDetailList = new ArrayList<>();
        foodOrderDetailList.add(genFoodOrderDetail());
        when(foodOrderDetailRepository.getAllByOrderId(1)).thenReturn(foodOrderDetailList);
        foodOrderDetailService.getAllByOrderId(1);
    }

    @Test
    public void getOneFoodOrderDetail() throws Exception {
        when(foodOrderDetailRepository.getOne(1)).thenReturn(genFoodOrderDetail());
        foodOrderDetailService.getOne(1);
    }


}
