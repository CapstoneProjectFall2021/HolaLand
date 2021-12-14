package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.repository.FoodOrderDetailRepository;
import com.hola.holalandfood.repository.FoodOrderRepository;
import com.hola.holalandfood.service.impl.FoodOrderDetailServiceImpl;
import com.hola.holalandfood.service.impl.FoodOrderServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FoodOrderServiceTest {
    @InjectMocks
    FoodOrderServiceImpl foodOrderService;

    @Mock
    FoodOrderRepository foodOrderRepository;

    public FoodOrder genFoodOrder(){
        FoodOrder foodOrder = FoodOrder.builder()
                .foodOrderId(1)
                .userId(1)
                .foodStoreOnlineId(1)
                .sttFoodCode(1)
                .foodOrderTotalPrice(15000)
                .foodOrderCreatedDate(Timestamp.valueOf("2018-09-01 09:01:15"))
                .foodOrderNote("ship nhanh giúp em")
                .foodOrderReasonReject("nhà anh hết cơm rồi")
                .build();
        return foodOrder;
    }

    @Test
    public void getAllFoodOrder() throws Exception {
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList.add(genFoodOrder());
        when(foodOrderRepository.getAll()).thenReturn(foodOrderList);
        foodOrderService.getAll();
    }

    @Test
    public void getAllUserOrderByUserIdAndStatus() throws Exception {
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList.add(genFoodOrder());
        when(foodOrderRepository.getAllUserOrderByUserIdAndStatus(1,1)).thenReturn(foodOrderList);
        foodOrderService.getAllUserOrderByUserIdAndStatus(1,1);
    }

    @Test
    public void getAllSellerOrderByUserIdAndStatus() throws Exception {
        List<FoodOrder> foodOrderList = new ArrayList<>();
        foodOrderList.add(genFoodOrder());
        when(foodOrderRepository.getAllSellerOrderByUserIdAndStatus(1,1)).thenReturn(foodOrderList);
        foodOrderService.getAllSellerOrderByUserIdAndStatus(1,1);
    }

    @Test
    public void getOneFoodOrder() throws Exception {
        when(foodOrderRepository.getOne(1)).thenReturn(genFoodOrder());
        foodOrderService.getOne(1);
    }

    @Test
    public void checkUserOrder() throws Exception {
        when(foodOrderRepository.checkUserOrder(1,1)).thenReturn(true);
        foodOrderService.checkUserOrder(1,1);
    }

    @Test
    public void updateSttFood() throws Exception {
        when(foodOrderRepository.updateSttFood(any())).thenReturn(true);
        foodOrderService.updateSttFood(genFoodOrder());
    }

    @Test
    public void addReasonReject() throws Exception {
        when(foodOrderRepository.addReasonReject(any())).thenReturn(true);
        foodOrderService.addReasonReject(genFoodOrder());
    }

}
