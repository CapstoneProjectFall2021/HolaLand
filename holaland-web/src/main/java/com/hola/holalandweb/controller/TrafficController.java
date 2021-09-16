package com.hola.holalandweb.controller;

import com.hola.holalandtraffic.entity.Bus;
import com.hola.holalandtraffic.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrafficController {

    private final BusService busService;

    @Autowired
    public TrafficController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/traffic")
    public String goToTraffic(Model model) {
        getTrafficInfo(model);
        return "traffic";
    }

    @GetMapping("/bus-detail")
    public String getBusDetail(@RequestParam("id") Integer id, Model model) {
        Bus bus = busService.getOne(id);
        model.addAttribute("bus", bus);
        getTrafficInfo(model);
        return "traffic";
    }

    private void getTrafficInfo(Model model) {
        List<Bus> busList = busService.getAll();
        model.addAttribute("busList", busList);
    }
}
