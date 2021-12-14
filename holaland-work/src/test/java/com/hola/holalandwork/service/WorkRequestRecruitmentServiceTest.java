package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.repository.WorkRequestRecruitmentRepository;
import com.hola.holalandwork.service.impl.WorkRequestRecruitmentServiceImpl;
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
public class WorkRequestRecruitmentServiceTest {
    @InjectMocks
    WorkRequestRecruitmentServiceImpl workRequestRecruitmentService;

    @Mock
    WorkRequestRecruitmentRepository workRequestRecruitmentRepository;

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
    public void getAllWorkRequestRecruitment() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(genWorkRequestRecruitment());
        when(workRequestRecruitmentRepository.getAll()).thenReturn(workRequestRecruitmentList);
        workRequestRecruitmentService.getAll();
    }

    @Test
    public void getOneWorkRequestRecruitment() throws Exception {
        when(workRequestRecruitmentRepository.getOne(1)).thenReturn(genWorkRequestRecruitment());
        workRequestRecruitmentService.getOne(1);
    }

    @Test
    public void getAllByTypeWorkRequestRecruitment() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(genWorkRequestRecruitment());
        when(workRequestRecruitmentRepository.getAllByType(1,1)).thenReturn(workRequestRecruitmentList);
        workRequestRecruitmentService.getAllByType(1,1);
    }

    @Test
    public void getAllByUserIdAndTypeId() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(genWorkRequestRecruitment());
        when(workRequestRecruitmentRepository.getAllByUserIdAndTypeId(1,1)).thenReturn(workRequestRecruitmentList);
        workRequestRecruitmentService.getAllByUserIdAndTypeId(1,1);
    }

    @Test
    public void getAllListAppliedByUserId() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(genWorkRequestRecruitment());
        when(workRequestRecruitmentRepository.getAllListAppliedByUserId(1,1)).thenReturn(workRequestRecruitmentList);
        workRequestRecruitmentService.getAllListAppliedByUserId(1,1);
    }

    @Test
    public void createWorkRequestRecruitment() throws Exception {
        when(workRequestRecruitmentRepository.save(any())).thenReturn(true);
        workRequestRecruitmentService.save(genWorkRequestRecruitment());
    }

    @Test
    public void deleteWorkRequestRecruitment() throws Exception {
        when(workRequestRecruitmentRepository.delete(1)).thenReturn(true);
        workRequestRecruitmentService.delete(1);
    }
}
