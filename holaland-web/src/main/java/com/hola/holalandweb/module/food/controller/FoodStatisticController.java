package com.hola.holalandweb.module.food.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.util.Calendars;
import com.hola.holalandfood.service.FoodOrderService;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/store")
public class FoodStatisticController {

    private final FoodOrderService foodOrderService;
    private final FoodStoreOnlineService foodStoreOnlineService;

    private int storeId;

    @Autowired
    public FoodStatisticController(
            FoodOrderService foodOrderService,
            FoodStoreOnlineService foodStoreOnlineService
    ) {
        this.foodOrderService = foodOrderService;
        this.foodStoreOnlineService = foodStoreOnlineService;
    }

    @GetMapping("/statistics")
    public String statistics(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        storeId = foodStoreOnlineService.getOneByUserId(currentUser.getId()).getFoodStoreOnlineId();

        int days = Calendars.getNumberOfDateInMonth(Calendars.getCurrentMonth(), Calendars.getCurrentYear());
        List<Integer> listOfDatesOfTheMonth = Calendars.getListOfDateOfTheMonth(days);
        List<Double> listMoneyOfDayOfTheMonth = foodOrderService.getListMoneyOfDayOfTheMonth(
                Calendars.getCurrentMonth(),
                Calendars.getCurrentYear(),
                storeId
        );
        List<Double> listNumberOfOrdersOfDay = foodOrderService.getListNumberOfOrdersOfDay(
                Calendars.getCurrentMonth(),
                Calendars.getCurrentYear(),
                storeId
        );

        summary(listMoneyOfDayOfTheMonth, listNumberOfOrdersOfDay, model);
        backToStatistics(listOfDatesOfTheMonth,
                listMoneyOfDayOfTheMonth,
                1,
                1,
                Calendars.getCurrentMonth(),
                Calendars.getCurrentYear(),
                model
        );
        model.addAttribute("page", 4);
        return "module-food-manage-store";
    }

    @PostMapping("statistics/month")
    public String test(
            @RequestParam("chartType") Integer chartType,
            @RequestParam("viewType") Integer viewType,
            @RequestParam("month") Integer month,
            @RequestParam("year") Integer year,
            Model model
    ) {
        int days = Calendars.getNumberOfDateInMonth(month, year);
        List<Integer> listOfDatesOfTheMonth = Calendars.getListOfDateOfTheMonth(days);
        List<Double> listMoneyOfDayOfTheMonth = foodOrderService.getListMoneyOfDayOfTheMonth(month, year, storeId);
        List<Double> listNumberOfOrdersOfDay = foodOrderService.getListNumberOfOrdersOfDay(month, year, storeId);

        if (viewType == 1) {
            backToStatistics(listOfDatesOfTheMonth, listMoneyOfDayOfTheMonth, chartType, viewType, month, year, model);
        }
        if (viewType == 2) {
            backToStatistics(listOfDatesOfTheMonth, listNumberOfOrdersOfDay, chartType, viewType, month, year, model);
        }
        summary(listMoneyOfDayOfTheMonth, listNumberOfOrdersOfDay, model);
        model.addAttribute("page", 4);
        return "module-food-manage-store";
    }

    private void backToStatistics(
            List<Integer> listOfDaysOfTheMonth,
            List<Double> listData,
            int chartType,
            int viewType,
            int month,
            int year,
            Model model
    ) {
        model.addAttribute("days", listOfDaysOfTheMonth);
        model.addAttribute("data", listData);
        model.addAttribute("listMonth", Calendars.getListMonth());
        model.addAttribute("selectChartType", chartType);
        model.addAttribute("selectViewType", viewType);
        model.addAttribute("selectMonth", month);
        model.addAttribute("currentYear", year);
    }

    private void summary(List<Double> listMoneyOfDayOfTheMonth, List<Double> listNumberOfOrdersOfDay, Model model) {
        double totalMoneyOfMonth = 0;
        for (Double d : listMoneyOfDayOfTheMonth) {
            totalMoneyOfMonth += d;
        }

        double totalOrderOfMonth = 0;
        for (Double i : listNumberOfOrdersOfDay) {
            totalOrderOfMonth += i;
        }

        model.addAttribute("totalMoneyOfMonth", totalMoneyOfMonth);
        model.addAttribute("totalOrderOfMonth", (int) totalOrderOfMonth);
    }
}
