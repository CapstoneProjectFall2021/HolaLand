package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import com.hola.holalandwork.repository.SttWorkRequestRecruitmentFindJobCountRepository;
import com.hola.holalandwork.service.impl.SttWorkRequestRecruitmentFindJobCountServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SttWorkRequestRecruitmentFindJobCountServiceTest {
    @InjectMocks
    SttWorkRequestRecruitmentFindJobCountServiceImpl sttWorkRequestRecruitmentFindJobCountService;

    @Mock
    SttWorkRequestRecruitmentFindJobCountRepository sttWorkRequestRecruitmentFindJobCountRepository;

    public SttWorkRequestRecruitmentFindJobCount genSttWorkRequestRecruitmentFindJobCount(){
        SttWorkRequestRecruitmentFindJobCount sttWorkRequestRecruitmentFindJobCount = SttWorkRequestRecruitmentFindJobCount
                .builder()
                .sttWorkRequestRecruitmentFindJobCountId(1)
                .userId(1)
                .sttWorkRequestRecruitmentFindJobCountPending(1)
                .sttWorkRequestRecruitmentFindJobCountReject(2)
                .sttWorkRequestRecruitmentFindJobCountApproved(3)
                .sttWorkRequestRecruitmentFindJobCountComplete(4)
                .sttWorkRequestRecruitmentFindJobCountExpired(5)
                .sttWorkRequestRecruitmentFindJobCountSaveDraft(6)
                .build();
        return sttWorkRequestRecruitmentFindJobCount;
    }

    @Test
    public void getOneByUserId() {
        when(sttWorkRequestRecruitmentFindJobCountRepository.getOneByUserId(1)).thenReturn(genSttWorkRequestRecruitmentFindJobCount());
        sttWorkRequestRecruitmentFindJobCountService.getOneByUserId(1);
    }


}
