package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.entity.FoodType;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import com.hola.holalandfood.service.FoodTypeService;
import com.hola.holalandweb.module.food.controller.FoodController;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class FoodControllerTest {
    @InjectMocks
    FoodController foodController;

    private MockMvc mockMvc;

    @Mock
    private FoodTypeService foodTypeService;

    @Mock
    private FoodStoreOnlineService foodStoreOnlineService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(foodController)
                .build();
    }

    @Test
    public void goToFood() throws Exception {
        FoodType foodType = new FoodType();
        foodType.setFoodTypeId(1);
        foodType.setFoodTypeName("Đồ ăn chính");
        foodType.setFoodTypeIcon("fas fa-hamburger");
        foodType.setFoodTypeCount(3);
        foodType.setFoodTypeDeleted(false);
        List<FoodType> foodTypeList = new ArrayList<>();
        foodTypeList.add(foodType);
        FoodStoreOnline foodStoreOnline = new FoodStoreOnline();
        foodStoreOnline.setFoodStoreOnlineId(1);
        foodStoreOnline.setUserId(1);
        foodStoreOnline.setSttFoodCode(1);
        foodStoreOnline.setFoodStoreOnlineImage("name.jpg");
        foodStoreOnline.setFoodStoreOnlineName("Quán cơm con gà");
        foodStoreOnline.setFoodStoreOnlineRate(5);
        foodStoreOnline.setFoodStoreOnlineMinPrice(15000);
        foodStoreOnline.setFoodStoreOnlineMaxPrice(20000);
        foodStoreOnline.setFoodStoreOnlineDescription("chuyên bán cơm");
        foodStoreOnline.setFoodStoreOnlineCountFoodItem(22);
        foodStoreOnline.setFoodStoreOnlineCountRate(5);
        foodStoreOnline.setFoodStoreOnlineCountReport(4);
        foodStoreOnline.setFoodStoreOnlinePauseSellingFlag(true);
        foodStoreOnline.setFoodStoreOnlineStopSellingFlag(true);
        foodStoreOnline.setFoodStoreOnlineDeleted(false);
        List<FoodStoreOnline> foodStoreOnlineList = new ArrayList<>();
        foodStoreOnlineList.add(foodStoreOnline);
        when(foodTypeService.getAll()).thenReturn(foodTypeList);
        when(foodStoreOnlineService.getAllByType(1,1)).thenReturn(foodStoreOnlineList);
        mockMvc.perform(get("/food"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-food"));
    }

    @Test
    public void getFoodStoreByType() throws Exception {
        FoodType foodType = new FoodType();
        foodType.setFoodTypeId(1);
        foodType.setFoodTypeName("Đồ ăn chính");
        foodType.setFoodTypeIcon("fas fa-hamburger");
        foodType.setFoodTypeCount(3);
        foodType.setFoodTypeDeleted(false);
        List<FoodType> foodTypeList = new ArrayList<>();
        foodTypeList.add(foodType);
        FoodStoreOnline foodStoreOnline = new FoodStoreOnline();
        foodStoreOnline.setFoodStoreOnlineId(1);
        foodStoreOnline.setUserId(1);
        foodStoreOnline.setSttFoodCode(1);
        foodStoreOnline.setFoodStoreOnlineImage("name.jpg");
        foodStoreOnline.setFoodStoreOnlineName("Quán cơm con gà");
        foodStoreOnline.setFoodStoreOnlineRate(5);
        foodStoreOnline.setFoodStoreOnlineMinPrice(15000);
        foodStoreOnline.setFoodStoreOnlineMaxPrice(20000);
        foodStoreOnline.setFoodStoreOnlineDescription("chuyên bán cơm");
        foodStoreOnline.setFoodStoreOnlineCountFoodItem(22);
        foodStoreOnline.setFoodStoreOnlineCountRate(5);
        foodStoreOnline.setFoodStoreOnlineCountReport(4);
        foodStoreOnline.setFoodStoreOnlinePauseSellingFlag(true);
        foodStoreOnline.setFoodStoreOnlineStopSellingFlag(true);
        foodStoreOnline.setFoodStoreOnlineDeleted(false);
        List<FoodStoreOnline> foodStoreOnlineList = new ArrayList<>();
        foodStoreOnlineList.add(foodStoreOnline);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("typeId","1");
        when(foodTypeService.getAll()).thenReturn(foodTypeList);
        when(foodStoreOnlineService.getAllByType(1,1)).thenReturn(foodStoreOnlineList);
        mockMvc.perform(get("/food/type").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-food"));
    }
    
}
