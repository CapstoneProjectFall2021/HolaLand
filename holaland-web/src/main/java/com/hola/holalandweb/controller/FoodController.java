package com.hola.holalandweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {

    @GetMapping("/food")
    public String goToFood(Model model) {
        model.addAttribute("page", 1);
        return "module-food";
    }

    @GetMapping("/food/online-store")
    public String goToOnlineStore(Model model) {
        model.addAttribute("page", 9);
        return "module-food";
    }

    @GetMapping("/food/list-offline-store")
    public String goToOfflineStore(Model model) {
        model.addAttribute("page", 2);
        return "module-food";
    }

    @GetMapping("/food/user-order")
    public String goToUserOrder(Model model) {
        model.addAttribute("page", 3);
        return "module-food";
    }

    @GetMapping("/food/manage-store")
    public String goToManageStore(Model model) {
        model.addAttribute("page", 1);
        return "module-food-manage-store";
    }
}
