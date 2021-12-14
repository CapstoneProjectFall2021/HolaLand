package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWork;
import com.hola.holalandwork.repository.SttWorkRepository;
import com.hola.holalandwork.service.impl.SttWorkServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SttWorkServiceTest {
    @InjectMocks
    SttWorkServiceImpl sttWorkService;

    @Mock
    SttWorkRepository sttWorkRepository;

    public SttWork genSttWork(){
        SttWork sttWork = SttWork.builder()
                .sttWorkId(1)
                .sttWorkName("tìm việc")
                .sttWorkCode(1)
                .sttWorkValue("pending")
                .sttWorkIcon("icon.png")
                .build();
        return sttWork;
    }

    @Test
    public void getAllSttWork() {
        List<SttWork> sttWorkList = new ArrayList<>();
        sttWorkList.add(genSttWork());
        when(sttWorkRepository.getAll()).thenReturn(sttWorkList);
        sttWorkService.getAll();
    }

    @Test
    public void getAllByName() {
        List<SttWork> sttWorkList = new ArrayList<>();
        sttWorkList.add(genSttWork());
        when(sttWorkRepository.getAllByName(any())).thenReturn(sttWorkList);
        sttWorkService.getAllByName("tìm việc");
    }

    @Test
    public void getOneSttWork() {
        when(sttWorkRepository.getOne(1)).thenReturn(genSttWork());
        sttWorkService.getOne(1);
    }
}
