package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.repository.WorkRequestFindJobRepository;
import com.hola.holalandwork.service.impl.WorkRequestFindJobServiceImpl;
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
public class WorkRequestFindJobServiceTest {
    @InjectMocks
    WorkRequestFindJobServiceImpl workRequestFindJobService;

    @Mock
    WorkRequestFindJobRepository workRequestFindJobRepository;

    public WorkRequestFindJob genWorkRequestFindJob() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = format.parse("23/10/2021");
        java.sql.Date date = new java.sql.Date(parsed.getTime());
        WorkRequestFindJob workRequestFindJob = WorkRequestFindJob.builder()
                .workRequestFindJobId(1)
                .userId(1)
                .workRequestTypeId(1)
                .workTimeId(1)
                .sttWorkCode(1)
                .workPaymentMethodId(1)
                .workSalaryUnitId(1)
                .workRequestFindJobTitle("Nhận làm gia sư lớp 1")
                .workRequestFindJobStartDateTime(date)
                .workRequestFindJobEndDateTime(date)
                .workRequestFindJobLastUpdateDateTime(date)
                .workRequestFindJobDescription("gia sư các môn toán văn anh")
                .workRequestFindJobPersonalExperience("học sinh giỏi 12 năm")
                .workRequestFindJobExpectedLocation("trong thành phố Hà Nội")
                .workRequestFindJobExpectedSalary(6000000)
                .workRequestFindJobNote("lương thỏa thuận")
                .build();
        return workRequestFindJob;
    }

    @Test
    public void getAllWorkRequestFindJob() throws Exception {
        List<WorkRequestFindJob> workRequestFindJobList = new ArrayList<>();
        workRequestFindJobList.add(genWorkRequestFindJob());
        when(workRequestFindJobRepository.getAll()).thenReturn(workRequestFindJobList);
        workRequestFindJobService.getAll();
    }

    @Test
    public void getOneWorkRequestFindJob() throws Exception {
        when(workRequestFindJobRepository.getOne(1)).thenReturn(genWorkRequestFindJob());
        workRequestFindJobService.getOne(1);
    }

    @Test
    public void getAllByTypeWorkRequestFindJob() throws Exception {
        List<WorkRequestFindJob> workRequestFindJobList = new ArrayList<>();
        workRequestFindJobList.add(genWorkRequestFindJob());
        when(workRequestFindJobRepository.getAllByType(1,1)).thenReturn(workRequestFindJobList);
        workRequestFindJobService.getAllByType(1,1);
    }

    @Test
    public void getAllByUserIdAndTypeId() throws Exception {
        List<WorkRequestFindJob> workRequestFindJobList = new ArrayList<>();
        workRequestFindJobList.add(genWorkRequestFindJob());
        when(workRequestFindJobRepository.getAllByUserIdAndTypeId(1,1)).thenReturn(workRequestFindJobList);
        workRequestFindJobService.getAllByUserIdAndTypeId(1,1);
    }

    @Test
    public void getAllListRecruitmentByUserId() throws Exception {
        List<WorkRequestFindJob> workRequestFindJobList = new ArrayList<>();
        workRequestFindJobList.add(genWorkRequestFindJob());
        when(workRequestFindJobRepository.getAllListRecruitmentByUserId(1,1)).thenReturn(workRequestFindJobList);
        workRequestFindJobService.getAllListRecruitmentByUserId(1,1);
    }

    @Test
    public void createWorkRequestFindJob() throws Exception {
        when(workRequestFindJobRepository.save(any())).thenReturn(true);
        workRequestFindJobService.save(genWorkRequestFindJob());
    }

    @Test
    public void deleteWorkRequestFindJob() throws Exception {
        when(workRequestFindJobRepository.delete(1)).thenReturn(true);
        workRequestFindJobService.delete(1);
    }
}
