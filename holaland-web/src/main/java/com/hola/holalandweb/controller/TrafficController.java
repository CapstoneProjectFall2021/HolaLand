package com.hola.holalandweb.controller;

import com.hola.holalandtraffic.entity.Bus;
import com.hola.holalandtraffic.entity.MotorbikeTaxiDrivers;
import com.hola.holalandtraffic.service.BusService;
import com.hola.holalandtraffic.service.MotorbikeTaxiDriversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrafficController {

    private final BusService busService;

    private final MotorbikeTaxiDriversService motorbikeTaxiDriversService;

    @Autowired
    public TrafficController(BusService busService, MotorbikeTaxiDriversService motorbikeTaxiDriversService) {
        this.busService = busService;
        this.motorbikeTaxiDriversService = motorbikeTaxiDriversService;
    }

    @GetMapping("/traffic")
    public String goToTraffic(Model model) {
        getTrafficInfo(model, 1);
        return "traffic";
    }

    @GetMapping("/traffic/bus-detail")
    public String getBusDetail(@RequestParam("id") Integer id, @RequestParam("page") Integer page, Model model) {
        Bus bus = busService.getOne(id);
        model.addAttribute("bus", bus);
        getTrafficInfo(model, page);
        return "traffic";
    }

    @GetMapping("/traffic/motorbike-taxi-drivers")
    public String getMotorbikeTaxiDriversDetail(@RequestParam("page") Integer page, Model model) {
        List<MotorbikeTaxiDrivers> motorbikeTaxiDriversList = motorbikeTaxiDriversService.getAll();
        model.addAttribute("motorbikeTaxiDriversList", motorbikeTaxiDriversList);
        model.addAttribute("page", page);
        return "traffic";
    }

    private void getTrafficInfo(Model model, int page) {
        List<Bus> busList = busService.getAll();
        model.addAttribute("busList", busList);
        model.addAttribute("page", page);
    }
}
