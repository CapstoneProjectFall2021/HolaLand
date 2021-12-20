package com.hola.holalandfood;

import com.hola.holalandfood.service.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FoodItemServiceTest.class,
        FoodOrderDetailServiceTest.class,
        FoodOrderServiceTest.class,
        FoodReportServiceTest.class,
        FoodReportServiceTest.class,
        FoodStoreOnlineRateServiceTest.class,
        FoodReportServiceTest.class,
        FoodStoreOnlineTagServiceTest.class,
        FoodStoreOnlineTypeServiceTest.class,
        FoodTagServiceTest.class,
        FoodTypeServiceTest.class,
        SttFoodServiceTest.class
})
class HolalandFoodApplicationTests {



}
