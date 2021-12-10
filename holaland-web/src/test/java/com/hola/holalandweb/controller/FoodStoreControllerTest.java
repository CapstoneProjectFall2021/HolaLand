package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.*;
import com.hola.holalandfood.service.*;
import com.hola.holalandweb.module.food.controller.FoodStoreController;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class FoodStoreControllerTest {
    @InjectMocks
    FoodStoreController foodStoreController;

    private MockMvc mockMvc;

    @Mock
    FoodStoreOnlineService foodStoreOnlineService;
    @Mock
    FoodTagService foodTagService;
    @Mock
    FoodStoreOnlineRateService foodStoreOnlineRateService;
    @Mock
    FoodReportService foodReportService;
    @Mock
    FoodItemService foodItemService;
    @Mock
    FoodOrderDetailService foodOrderDetailService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(foodStoreController)
                .build();
    }

    public FoodStoreOnline setAttrFoodStoreOnline(){
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
        foodStoreOnline.setFoodStoreOnlinePauseSellingFlag(false);
        foodStoreOnline.setFoodStoreOnlineStopSellingFlag(false);
        foodStoreOnline.setFoodStoreOnlineDeleted(false);
        return foodStoreOnline;
    }

    public FoodTag setAttrFoodTag(){
        FoodTag foodTag = new FoodTag();
        foodTag.setFoodTagId(1);
        foodTag.setFoodTagName("Cơm");
        return foodTag;
    }

    public FoodStoreOnlineRate setAttrFoodStoreOnlineRate(){
        FoodStoreOnlineRate foodStoreOnlineRate = new FoodStoreOnlineRate();
        foodStoreOnlineRate.setFoodStoreOnlineRateId(1);
        foodStoreOnlineRate.setUserId(1);
        foodStoreOnlineRate.setFoodStoreOnlineId(1);
        foodStoreOnlineRate.setFoodStoreOnlineRatePoint(4);
        foodStoreOnlineRate.setFoodStoreOnlineRateComment("ngon quá");
        foodStoreOnlineRate.setFoodStoreOnlineRateCreateTime(Timestamp.valueOf("2021-10-22 10:20:43"));
        foodStoreOnlineRate.setFoodStoreOnlineRateUpdateTime(Timestamp.valueOf("2021-10-22 10:20:43"));
        foodStoreOnlineRate.setFoodStoreOnlineDeleted(false);
        return foodStoreOnlineRate;
    }

    public FoodReport  setAttrFoodReport(){
        FoodReport foodReport = new FoodReport();
        foodReport.setFoodReportId(1);
        foodReport.setUserId(1);
        foodReport.setFoodStoreOnlineId(1);
        foodReport.setFoodOrderId(1);
        foodReport.setFoodReportContent("cơm thiu");
        foodReport.setFoodReportCreateDate(Timestamp.valueOf("2021-10-22 10:20:43"));
        foodReport.setFoodReportUpdateDate(Timestamp.valueOf("2021-10-22 10:20:43"));
        foodReport.setFoodReportDeleted(false);
        return foodReport;
    }

    public FoodItem setAttrFoodItem(){
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodItemId(1);
        foodItem.setFoodStoreOnlineId(1);
        foodItem.setFoodTagId(1);
        foodItem.setFoodTypeId(1);
        foodItem.setFoodItemImage("image.jpg");
        foodItem.setFoodItemName("cơm chiên trứng");
        foodItem.setFoodItemPrice(30000);
        foodItem.setFoodItemSoldNumber(10);
        foodItem.setFoodItemIsActive(1);
        foodItem.setFoodItemDeleted(1);
        return foodItem;
    }

    public FoodOrderDetail setAttrFoodOrderDetail(){
        FoodOrderDetail foodOrderDetail = new FoodOrderDetail();
        foodOrderDetail.setFoodOrderDetailId(1);
        foodOrderDetail.setFoodOrderId(1);
        foodOrderDetail.setFoodItemId(1);
        foodOrderDetail.setFoodItemName("Khô gà");
        foodOrderDetail.setFoodItemPrice(35000);
        foodOrderDetail.setFoodItemQuantity(5);
        return foodOrderDetail;
    }

    @Test
    public void goToOnlineStore() throws Exception {
        //FoodTag
        List<FoodTag> foodTagList = new ArrayList<>();
        foodTagList.add(setAttrFoodTag());
        //Food Rate
        List<FoodStoreOnlineRate> foodStoreOnlineRateList = new ArrayList<>();
        foodStoreOnlineRateList.add(setAttrFoodStoreOnlineRate());
        //FoodReport
        List<FoodReport> foodReportList = new ArrayList<>();
        foodReportList.add(setAttrFoodReport());
        //FoodItems
        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(setAttrFoodItem());

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");
        when(foodStoreOnlineService.getOne(1)).thenReturn(setAttrFoodStoreOnline());
        when(foodTagService.getAllByStoreOnlineId(1)).thenReturn(foodTagList);
        when(foodStoreOnlineRateService.getAllCommentByStoreOnlineId(1)).thenReturn(foodStoreOnlineRateList);
        when(foodReportService.getAllByOrderId(1)).thenReturn(foodReportList);
        when(foodItemService.getAllByStoreOnlineIdAndTagId(1, 1)).thenReturn(foodItemList);
        mockMvc.perform(get("/food/store").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-food"));
    }

    @Test
    public void getFoodOrderDetailReportTest() throws Exception {
        //FoodOrderDetail
        List<FoodOrderDetail> foodOrderDetailList = new ArrayList<>();
        foodOrderDetailList.add(setAttrFoodOrderDetail());
        //FoodStoreOnline
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("orderId", "1");
        when(foodOrderDetailService.getAllByOrderId(1)).thenReturn(foodOrderDetailList);
        when(foodStoreOnlineService.getOneByOrderId(1)).thenReturn(setAttrFoodStoreOnline());
        mockMvc.perform(get("/food/store/report/order/detail").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-food"));
    }

    @Test
    public void getFoodOnlineStoreByTagTest() throws Exception{
        //FoodTag
        List<FoodTag> foodTagList = new ArrayList<>();
        foodTagList.add(setAttrFoodTag());
        //Food Rate
        List<FoodStoreOnlineRate> foodStoreOnlineRateList = new ArrayList<>();
        foodStoreOnlineRateList.add(setAttrFoodStoreOnlineRate());
        //FoodReport
        List<FoodReport> foodReportList = new ArrayList<>();
        foodReportList.add(setAttrFoodReport());
        //FoodItems
        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(setAttrFoodItem());

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("storeId", "1");
        requestParams.add("tagId", "1");
        when(foodStoreOnlineService.getOne(1)).thenReturn(setAttrFoodStoreOnline());
        when(foodTagService.getAllByStoreOnlineId(1)).thenReturn(foodTagList);
        when(foodStoreOnlineRateService.getAllCommentByStoreOnlineId(1)).thenReturn(foodStoreOnlineRateList);
        when(foodReportService.getAllByOrderId(1)).thenReturn(foodReportList);
        when(foodItemService.getAllByStoreOnlineIdAndTagId(1, 1)).thenReturn(foodItemList);
        mockMvc.perform(get("/food/store/tag").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-food"));
    }


}
