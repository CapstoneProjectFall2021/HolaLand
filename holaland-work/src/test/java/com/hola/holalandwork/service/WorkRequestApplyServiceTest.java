package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.repository.WorkRequestApplyRepository;
import com.hola.holalandwork.service.impl.WorkRequestApplyServiceImpl;
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
public class WorkRequestApplyServiceTest {
    @InjectMocks
    WorkRequestApplyServiceImpl workRequestApplyService;

    @Mock
    WorkRequestApplyRepository workRequestApplyRepository;

    public WorkRequestApply genWorkRequestApply(){
        WorkRequestApply workRequestApply = WorkRequestApply.builder()
                .workRequestApplyId(1)
                .userId(1)
                .workRequestRecruitmentId(1)
                .sttWorkCode(1)
                .build();
        return workRequestApply;
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
    public void getAllWorkReport() {
        List<WorkRequestApply> workRequestApplyList = new ArrayList<>();
        workRequestApplyList.add(genWorkRequestApply());
        when(workRequestApplyRepository.getAll()).thenReturn(workRequestApplyList);
        workRequestApplyService.getAll();
    }

    @Test
    public void getAllAccountId() throws Exception{
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(genWorkRequestRecruitment());
        when(workRequestApplyRepository.getAllByAccountId(1)).thenReturn(workRequestRecruitmentList);
        workRequestApplyService.getAllAccountId(1);
    }

    @Test
    public void getOneWorkReport() {
        when(workRequestApplyRepository.getOne(1)).thenReturn(genWorkRequestApply());
        workRequestApplyService.getOne(1);
    }

    @Test
    public void createWorkReport() {
        when(workRequestApplyRepository.save(any())).thenReturn(true);
        workRequestApplyService.save(genWorkRequestApply());
    }

    @Test
    public void deleteWorkReport() {
        when(workRequestApplyRepository.delete(1)).thenReturn(true);
        workRequestApplyService.delete(1);
    }
}
