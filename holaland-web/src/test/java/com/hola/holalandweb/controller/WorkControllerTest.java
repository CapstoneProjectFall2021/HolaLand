package com.hola.holalandweb.controller;

import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandweb.module.works.controller.WorksController;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.service.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorkControllerTest {
    @InjectMocks
    WorksController worksController;

    private MockMvc mockMvc;

    @Mock
    WorkRequestRecruitmentService workRequestRecruitmentService;

    @Mock
    WorkRequestTypeService workRequestTypeService;

    @Mock
    WorkRequestApplyService workRequestApplyService;

    @Mock
    WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService;

    @Mock
    WorkRequestFindJobService workRequestFindJobService;

    @Mock
    WorkPaymentMethodService workPaymentMethodService;

    @Mock
    WorkTimeService workTimeService;

    @Mock
    UserDetailService userDetailService;

    @Mock
    SttWorkService sttWorkService;

    @Mock
    SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(worksController)
                .build();
    }

    public WorkRequestRecruitment setAttrWorkRequestRecruitment(){
        WorkRequestRecruitment workRequestRecruitment = WorkRequestRecruitment.builder()
                .workRequestRecruitmentId(1)
                .userId(1)
                .workPaymentMethodId(1)
                .workRequestTypeId(1)
                .sttWorkCode(1)
                .workSalaryUnitId(1)
                .workRequestRecruitmentTitle("Tuyển dụng IT")
                .workRequestRecruitmentStartDateTime(Date.valueOf("22/10//2021"))
                .workRequestRecruitmentEndDateTime(Date.valueOf("23/10//2021"))
                .workRequestRecruitmentLastUpdateDateTime(Date.valueOf("22/10//2021"))
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
    public void deleteJobsSaveRequestTestFail() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("requestId","1");
        when(workRequestRecruitmentSavedService.delete(1)).thenReturn(false);
        mockMvc.perform(get("/works/jobs-save/delete").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test
    public void deleteJobsSaveRequestTestPass() throws Exception {
        WorkRequestRecruitment workRequestRecruitment = WorkRequestRecruitment.builder()
                .workRequestRecruitmentId(1)
                .userId(1)
                .workPaymentMethodId(1).build();
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(workRequestRecruitment);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("requestId","1");

        when(workRequestRecruitmentSavedService.delete(1)).thenReturn(true);
        when(workRequestRecruitmentSavedService.getAllByAccountId(1)).thenReturn(workRequestRecruitmentList);
        mockMvc.perform(get("/works/jobs-save/delete").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }


}
