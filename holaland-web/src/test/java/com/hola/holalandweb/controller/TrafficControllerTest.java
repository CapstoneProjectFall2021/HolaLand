package com.hola.holalandweb.controller;

import com.hola.holalandtraffic.entity.Bus;
import com.hola.holalandtraffic.entity.MotorbikeTaxiDrivers;
import com.hola.holalandtraffic.service.BusService;
import com.hola.holalandtraffic.service.MotorbikeTaxiDriversService;
import com.hola.holalandweb.module.traffic.controller.TrafficController;
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
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TrafficControllerTest {
    @InjectMocks
    TrafficController trafficController;

    private MockMvc mockMvc;

    @Mock
    BusService busService;

    @Mock
    MotorbikeTaxiDriversService motorbikeTaxiDriversService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(trafficController)
                .build();
    }

    public Bus setAttrBus(){
        Bus bus = Bus.builder()
                .tfBusId(1)
                .tfBusName("74")
                .tfBusStartTime("7:00")
                .tfBusEndTime("20:00")
                .tfBusInfo("xe bus")
                .tfBusStops(Arrays.asList("a","b","c"))
                .tfBusPrice(9000)
                .tfBusStatus(1)
                .tfBusDeleted(false).build();
        return bus;
    }

    public MotorbikeTaxiDrivers setAttrMotorbikeTaxiDrivers(){
        MotorbikeTaxiDrivers motorbikeTaxiDrivers = MotorbikeTaxiDrivers.builder()
                .tfMotorbikeTaxiDriversId(1)
                .tfMotorbikeTaxiDriversName("Nguyễn Văn A")
                .tfMotorbikeTaxiDriversGender(true)
                .tfMotorbikeTaxiDriversPhone("1234567")
                .tfMotorbikeTaxiDriversImage("image.jpg")
                .tfMotorbikeTaxiDriversLicensePlates("a")
                .tfMotorbikeTaxiDriversVehicleType("xe máy honda")
                .tfMotorbikeTaxiDriversStartTime("7")
                .tfMotorbikeTaxiDriversEndTime("20")
                .tfMotorbikeTaxiDriversStatus(1)
                .tfMotorbikeTaxiDriversDeleted(false)
                .build();
        return motorbikeTaxiDrivers;
    }

    @Test
    public void goToTrafficTest() throws Exception{
        List<Bus> busList = new ArrayList<>();
        busList.add(setAttrBus());
        when(busService.getAll()).thenReturn(busList);
        mockMvc.perform(get("/traffic"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-traffic"));
    }

    @Test
    public void getBusDetailTest() throws Exception{
        List<Bus> busList = new ArrayList<>();
        busList.add(setAttrBus());
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");
        requestParams.add("page", "1");
        when(busService.getAll()).thenReturn(busList);
        when(busService.getOne(1)).thenReturn(setAttrBus());
        mockMvc.perform(get("/traffic/bus-detail").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-traffic"));
    }

    @Test
    public void getMotorbikeTaxiDriversDetailTest() throws Exception{
        List<MotorbikeTaxiDrivers> motorbikeTaxiDriversList = new ArrayList<>();
        motorbikeTaxiDriversList.add(setAttrMotorbikeTaxiDrivers());
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "1");
        when(motorbikeTaxiDriversService.getAll()).thenReturn(motorbikeTaxiDriversList);
        mockMvc.perform(get("/traffic/motorbike-taxi-drivers").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-traffic"));
    }
}
