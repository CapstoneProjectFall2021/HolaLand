package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.service.*;
import com.hola.holalandfood.view.FoodCountSttOrder;
import com.hola.holalandweb.module.food.controller.FoodOrderController;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class FoodOrderControllerTest {
    @InjectMocks
    FoodOrderController foodOrderController;

    private MockMvc mockMvc;

    @Mock
    FoodOrderService foodOrderService;

    @Mock
    FoodCountSttOrderService foodCountSttOrderService;

     @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(foodOrderController)
                .build();
    }

    public FoodOrder setAttrFoodOrder(){
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setFoodOrderId(1);
        foodOrder.setUserId(1);
        foodOrder.setFoodStoreOnlineId(1);
        foodOrder.setSttFoodCode(1);
        foodOrder.setFoodOrderTotalPrice(15000);
        foodOrder.setFoodOrderCreatedDate(Timestamp.valueOf("2021-10-22 10:20:43"));
        foodOrder.setFoodOrderNote("ship som giup em");
        foodOrder.setFoodOrderReasonReject("com chan qua");
        foodOrder.setFoodOrderDeleted(false);
        return foodOrder;
    }

    public FoodCountSttOrder setAttrFoodCountSttOrder(){
        FoodCountSttOrder foodCountSttOrder = new FoodCountSttOrder();
        foodCountSttOrder.setRejectOrder(1);
        foodCountSttOrder.setCompleted(1);
        foodCountSttOrder.setCancel(1);
        return foodCountSttOrder;
    }

    //Mấy hàm bên FoodOrderController có param Authentication ko test đc anh ạ
}
