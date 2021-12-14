package com.hola.holalandtraffic.service;

import com.hola.holalandtraffic.entity.Bus;
import com.hola.holalandtraffic.repository.BusRepository;
import com.hola.holalandtraffic.service.impl.BusServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceTest {
    @InjectMocks
    BusServiceImpl busService;

    @Mock
    BusRepository busRepository;

    public Bus genBus(){
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

    @Test
    public void getAllBus() throws Exception {
        List<Bus> busList = new ArrayList<>();
        busList.add(genBus());
        when(busRepository.getAll()).thenReturn(busList);
        busService.getAll();
    }

    @Test
    public void getOneBus() throws Exception {
        when(busRepository.getOne(1)).thenReturn(genBus());
        busService.getOne(1);
    }
}
