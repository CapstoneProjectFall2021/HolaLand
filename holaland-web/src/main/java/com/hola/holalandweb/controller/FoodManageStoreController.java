package com.hola.holalandweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodManageStoreController {

    @GetMapping("/store/info")
    public String getShopInfo(Model model) {
        model.addAttribute("page", 1);
        return "module-food-manage-store";
    }

    @GetMapping("/store/manage-food")
    public String manageFood(Model model) {
        model.addAttribute("page", 2);
        return "module-food-manage-store";
    }

    @GetMapping("/store/manage-order")
    public String manageOrder(Model model) {
        model.addAttribute("page", 3);
        return "module-food-manage-store";
    }

    @GetMapping("/store/statistics")
    public String statistics(Model model) {
        model.addAttribute("page", 4);
        return "module-food-manage-store";
    }
}
