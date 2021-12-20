package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkRequestRecruitmentSaved;
import com.hola.holalandwork.repository.WorkRequestRecruitmentSavedRepository;
import com.hola.holalandwork.service.impl.WorkRequestRecruitmentSavedServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkRequestRecruitmentSavedServiceTest {
    @InjectMocks
    WorkRequestRecruitmentSavedServiceImpl workRequestRecruitmentSavedService;

    @Mock
    WorkRequestRecruitmentSavedRepository workRequestRecruitmentSavedRepository;

    public WorkRequestRecruitmentSaved genWorkRequestRecruitmentSaved(){
        WorkRequestRecruitmentSaved workRequestRecruitmentSaved = WorkRequestRecruitmentSaved.builder()
                .workRequestRecruitmentSavedId(1)
                .userId(1)
                .workRequestRecruitmentId(1)
                .build();
        return workRequestRecruitmentSaved;
    }

    public WorkRequestRecruitment genWorkRequestRecruitment() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = format.parse("23/10/2021");
        java.sql.Date date = new java.sql.Date(parsed.getTime());
        WorkRequestRecruitment workRequestRecruitment = WorkRequestRecruitment.builder()
                .workRequestRecruitmentId(1)
                .userId(1)
                .workPaymentMethodId(1)
                .workRequestTypeId(1)
                .sttWorkCode(1)
                .workSalaryUnitId(1)
                .workRequestRecruitmentTitle("Tuyển dụng IT")
                .workRequestRecruitmentStartDateTime(date)
                .workRequestRecruitmentEndDateTime(date)
                .workRequestRecruitmentLastUpdateDateTime(date)
                .workRequestRecruitmentDescription("Code Front-end và Back-end")
                .workRequestRecruitmentRequirement("5 năm kinh nghiệm")
                .workRequestRecruitmentBenefit("nhiều đãi ngộ của công ty")
                .workRequestRecruitmentSalary("10 triệu - 20 triệu")
                .workRequestRecruitmentQuantity(3)
                .workRequestRecruitmentExperienceRequirement(true)
                .workRequestRecruitmentGenderRequirement(true)
                .workRequestRecruitmentWorkLocation("Hà Nội")
                .workRequestRecruitmentNote("a")
                .workRequestRecruitmentDeleted(false)
                .build();
        return workRequestRecruitment;
    }

    @Test
    public void getAllWorkRequestRecruitmentSaved() throws Exception {
        List<WorkRequestRecruitmentSaved> workRequestRecruitmentSavedList = new ArrayList<>();
        workRequestRecruitmentSavedList.add(genWorkRequestRecruitmentSaved());
        when(workRequestRecruitmentSavedRepository.getAll()).thenReturn(workRequestRecruitmentSavedList);
        workRequestRecruitmentSavedService.getAll();
    }

    @Test
    public void getAllByAccountId() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(genWorkRequestRecruitment());
        when(workRequestRecruitmentSavedRepository.getAllByAccountId(1)).thenReturn(workRequestRecruitmentList);
        workRequestRecruitmentSavedService.getAllByAccountId(1);
    }

    @Test
    public void getOneWorkRequestRecruitmentSaved() throws Exception {
        when(workRequestRecruitmentSavedRepository.getOne(1)).thenReturn(genWorkRequestRecruitmentSaved());
        workRequestRecruitmentSavedService.getOne(1);
    }

    @Test
    public void createWorkRequestRecruitmentSaved() throws Exception {
        when(workRequestRecruitmentSavedRepository.save(any())).thenReturn(true);
        workRequestRecruitmentSavedService.save(genWorkRequestRecruitmentSaved());
    }

    @Test
    public void deleteWorkRequestRecruitmentSaved() throws Exception {
        when(workRequestRecruitmentSavedRepository.delete(1)).thenReturn(true);
        workRequestRecruitmentSavedService.delete(1);
    }
}
