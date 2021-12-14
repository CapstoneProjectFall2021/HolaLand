package com.hola.holalandtraffic.service;

import com.hola.holalandtraffic.entity.MotorbikeTaxiDrivers;
import com.hola.holalandtraffic.repository.MotorbikeTaxiDriversRepository;
import com.hola.holalandtraffic.service.impl.MotorbikeTaxiDriversServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MotorbikeTaxiDriversServiceTest {
    @InjectMocks
    MotorbikeTaxiDriversServiceImpl motorbikeTaxiDriversService;

    @Mock
    MotorbikeTaxiDriversRepository motorbikeTaxiDriversRepository;

    public MotorbikeTaxiDrivers genMotorbikeTaxiDrivers(){
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
    public void getAllMotorbikeTaxiDrivers() throws Exception {
        List<MotorbikeTaxiDrivers> motorbikeTaxiDriversList = new ArrayList<>();
        motorbikeTaxiDriversList.add(genMotorbikeTaxiDrivers());
        when(motorbikeTaxiDriversRepository.getAll()).thenReturn(motorbikeTaxiDriversList);
        motorbikeTaxiDriversService.getAll();
    }

    @Test
    public void getOneMotorbikeTaxiDrivers() throws Exception {
        when(motorbikeTaxiDriversRepository.getOne(1)).thenReturn(genMotorbikeTaxiDrivers());
        motorbikeTaxiDriversService.getOne(1);
    }
}
