package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandweb.module.works.controller.WorksController;
import com.hola.holalandwork.entity.*;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public WorkRequestRecruitment setAttrWorkRequestRecruitment() throws ParseException {
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

    public WorkRequestType setAttrWorkRequestType() {
        WorkRequestType workRequestType = WorkRequestType.builder()
                .workRequestTypeId(1)
                .workRequestTypeIcon("icon.png")
                .workRequestTypeName("bán thời gian")
                .workRequestTypeCountRequestRecruitment(2)
                .workRequestTypeCountRequestFindJob(3)
                .build();
        return workRequestType;
    }

    public WorkRequestApply setAttrWorkRequestApply() {
        WorkRequestApply workRequestApply = WorkRequestApply.builder()
                .workRequestApplyId(1)
                .userId(1)
                .workRequestRecruitmentId(1)
                .sttWorkCode(1)
                .build();
        return workRequestApply;
    }

    public WorkRequestFindJob setAttrWorkRequestFindJob() throws ParseException {
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

    public SttWork setAttrSttWork() {
        SttWork sttWork = SttWork.builder()
                .sttWorkId(1)
                .sttWorkName("work_find_job")
                .sttWorkCode(1)
                .sttWorkValue("Approve")
                .sttWorkIcon("icon.png")
                .build();
        return sttWork;
    }

    public WorkPaymentMethod setAttrWorkPaymentMethod(){
        WorkPaymentMethod workPaymentMethod = WorkPaymentMethod.builder()
                .workPaymentMethodId(1)
                .workPaymentMethodName("ngày")
                .build();
        return workPaymentMethod;
    }

    public WorkTime setAttrWorkTime(){
        WorkTime workTime = WorkTime.builder()
                .workTimeId(1)
                .workTimeName("sáng")
                .build();
        return workTime;
    }

    public SttWorkRequestRecruitmentFindJobCount setAttrSttWorkRequestRecruitmentFindJobCount(){
        SttWorkRequestRecruitmentFindJobCount sttWorkRequestRecruitmentFindJobCount = SttWorkRequestRecruitmentFindJobCount
                .builder()
                .sttWorkRequestRecruitmentFindJobCountId(1)
                .userId(1)
                .sttWorkRequestRecruitmentFindJobCountPending(1)
                .sttWorkRequestRecruitmentFindJobCountReject(1)
                .sttWorkRequestRecruitmentFindJobCountApproved(1)
                .sttWorkRequestRecruitmentFindJobCountComplete(1)
                .sttWorkRequestRecruitmentFindJobCountExpired(1)
                .sttWorkRequestRecruitmentFindJobCountSaveDraft(1)
                .build();
        return sttWorkRequestRecruitmentFindJobCount;
    }

    public UserDetail setAttrUserDetail() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = format.parse("22/10/1999");
        java.sql.Date date = new java.sql.Date(parsed.getTime());
        UserDetail userDetail = UserDetail.builder()
                .userDetailId(1)
                .userId(1)
                .userDetailName("danchoi@gmail.com")
                .userDetailDob(date)
                .userDetailGender(1)
                .userDetailPhone("0839696866")
                .userDetailEmail("a")
                .build();
        return userDetail;
    }

    @Test
    public void goToWorksTest() throws Exception {
        List<WorkRequestType> workRequestTypeList = new ArrayList<>();
        workRequestTypeList.add(setAttrWorkRequestType());
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(setAttrWorkRequestRecruitment());
        when(workRequestTypeService.getAll()).thenReturn(workRequestTypeList);
        when(workRequestRecruitmentService.getAllByType(1,1)).thenReturn(workRequestRecruitmentList);
        mockMvc.perform(get("/works"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void getWorkJobTypeTest() throws Exception {
        List<WorkRequestType> workRequestTypeList = new ArrayList<>();
        workRequestTypeList.add(setAttrWorkRequestType());
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(setAttrWorkRequestRecruitment());
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("workJobTypeId","1");
        when(workRequestTypeService.getAll()).thenReturn(workRequestTypeList);
        when(workRequestRecruitmentService.getAllByType(1,1)).thenReturn(workRequestRecruitmentList);
        mockMvc.perform(get("/works/type").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void getJobsApplyTest() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(setAttrWorkRequestRecruitment());
        when(workRequestApplyService.getAllAccountId(1)).thenReturn(workRequestRecruitmentList);
        mockMvc.perform(get("/works/jobs-apply"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void deleteSuccessJobsApplyRequest() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(setAttrWorkRequestRecruitment());
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("requestId","1");
        when(workRequestApplyService.delete(1)).thenReturn(true);
        when(workRequestApplyService.getAllAccountId(1)).thenReturn(workRequestRecruitmentList);
        mockMvc.perform(get("/works/jobs-apply/delete").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void deleteFailJobsApplyRequest() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("requestId","1");
        when(workRequestApplyService.delete(1)).thenReturn(false);
        mockMvc.perform(get("/works/jobs-apply/delete").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test
    public void getJobsSaveTest() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(setAttrWorkRequestRecruitment());
        when(workRequestRecruitmentSavedService.getAllByAccountId(1)).thenReturn(workRequestRecruitmentList);
        mockMvc.perform(get("/works/jobs-save"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void deleteSuccessJobsSaveRequestTest() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(setAttrWorkRequestRecruitment());
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("requestId","1");
        when(workRequestRecruitmentSavedService.delete(1)).thenReturn(true);
        when(workRequestRecruitmentSavedService.getAllByAccountId(1)).thenReturn(workRequestRecruitmentList);
        mockMvc.perform(get("/works/jobs-save/delete").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void deleteFailJobsSaveRequestTest() throws Exception {
        List<WorkRequestRecruitment> workRequestRecruitmentList = new ArrayList<>();
        workRequestRecruitmentList.add(setAttrWorkRequestRecruitment());
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("requestId","1");
        when(workRequestRecruitmentSavedService.delete(1)).thenReturn(false);
        when(workRequestRecruitmentSavedService.getAllByAccountId(1)).thenReturn(workRequestRecruitmentList);
        mockMvc.perform(get("/works/jobs-save/delete").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test
    public void getWorkerListTest() throws Exception {
        List<WorkRequestType> workRequestTypeList = new ArrayList<>();
        workRequestTypeList.add(setAttrWorkRequestType());
        List<WorkRequestFindJob> workRequestFindJobList = new ArrayList<>();
        workRequestFindJobList.add(setAttrWorkRequestFindJob());
        when(workRequestTypeService.getAll()).thenReturn(workRequestTypeList);
        when(workRequestFindJobService.getAllByType(1,1)).thenReturn(workRequestFindJobList);
        mockMvc.perform(get("/works/request-find-job"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void getRequestRecruitmentDetailTest() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id","1");
        when(workRequestRecruitmentService.getOne(1)).thenReturn(setAttrWorkRequestRecruitment());
        when(workRequestTypeService.getOne(1)).thenReturn(setAttrWorkRequestType());
        mockMvc.perform(get("/works/request-recruitment-detail").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void getRequestFindJobDetailTest() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id","1");
        when(workRequestFindJobService.getOne(1)).thenReturn(setAttrWorkRequestFindJob());
        when(workRequestTypeService.getOne(1)).thenReturn(setAttrWorkRequestType());
        when(workPaymentMethodService.getOne(1)).thenReturn(setAttrWorkPaymentMethod());
        when(workTimeService.getOne(1)).thenReturn(setAttrWorkTime());
        mockMvc.perform(get("/works/request-find-job-detail").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void getFormCreateRequestFindJobTest() throws Exception {
        mockMvc.perform(get("/works/create-request-find-job"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    @Test
    public void getFormCreateRequestRecruitmentTest() throws Exception {
        mockMvc.perform(get("/works/create-request-recruitment"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-works"));
    }

    










}
