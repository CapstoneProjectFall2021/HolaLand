package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.*;
import com.hola.holalandfood.service.*;
import com.hola.holalandweb.module.food.controller.FoodManageStoreController;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class FoodManageStoreControllerTest {
    @InjectMocks
    FoodManageStoreController foodManageStoreController;

    private MockMvc mockMvc;

    @Mock
    FoodItemService foodItemService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(foodManageStoreController)
                .build();
    }

    @Test
    public void deleteFoodManagerStoreTestFail() throws Exception{
        FoodItem foodItem = FoodItem.builder()
                .foodItemId(1)
                .foodItemDeleted(1)
                .build();
        when(foodItemService.deletedOne(foodItem)).thenReturn(false);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("foodId", "1");
        mockMvc.perform(get("/store/manage/food/delete").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));

    }
}
