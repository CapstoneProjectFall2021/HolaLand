package com.hola.holalandweb.module.works.controller;

import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.*;
import com.hola.holalandwork.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/works")

public class WorksMemberController {

    private final WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService;
    private final SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService;
    private final WorkRequestTypeService workRequestTypeService;
    private final WorkRequestFindJobService workRequestFindJobService;
    private final SttWorkService sttWorkService;
    private final WorkPaymentMethodService workPaymentMethodService;
    private final WorkTimeService workTimeService;
    private final UserDetailService userDetailService;

    @Autowired
    public WorksMemberController(WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService,
                                 SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService,
                                 WorkRequestTypeService workRequestTypeService,
                                 WorkRequestFindJobService workRequestFindJobService,
                                 SttWorkService sttWorkService,
                                 WorkPaymentMethodService workPaymentMethodService,
                                 WorkTimeService workTimeService,
                                 UserDetailService userDetailService) {
        this.workRequestRecruitmentSavedService = workRequestRecruitmentSavedService;
        this.sttWorkRequestRecruitmentFindJobCountService = sttWorkRequestRecruitmentFindJobCountService;
        this.workRequestTypeService = workRequestTypeService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.sttWorkService = sttWorkService;
        this.workPaymentMethodService = workPaymentMethodService;
        this.workTimeService = workTimeService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/jobs-save")
    public String getJobsSave(Model model) {
        List<WorkRequestRecruitment> jobSaveList = workRequestRecruitmentSavedService.getAllByAccountId(2);
        model.addAttribute("jobSaveList", jobSaveList);
        model.addAttribute("page", 4);
        return "module-works";
    }

    @GetMapping("/jobs-save/delete")
    public String deleteJobsSaveRequest(@RequestParam("requestId") Integer requestId, Model model) {
        boolean isCheck = workRequestRecruitmentSavedService.delete(requestId);
        if (isCheck) {
            List<WorkRequestRecruitment> jobSaveList = workRequestRecruitmentSavedService.getAllByAccountId(2);
            model.addAttribute("jobSaveList", jobSaveList);
            model.addAttribute("page", 4);
            return "module-works";
        } else {
            return "404";
        }
    }

    @GetMapping("/request-find-job")
    public String getWorkerList(Model model) {
        List<WorkRequestType> requestTypeList = workRequestTypeService.getAll();
        List<WorkRequestFindJob> workerList = workRequestFindJobService.getAllByType(
                requestTypeList.get(0).getWorkRequestTypeId(),
                Constants.STT_WORK_CODE_APPROVED
        );

        model.addAttribute("workRequestTypeId", 1);
        model.addAttribute("requestTypeList", requestTypeList);
        model.addAttribute("workerList", workerList);
        model.addAttribute("page", 7);
        return "module-works";
    }

    @GetMapping("/request-find-job/type")
    public String getWorkerRequestType(
            @RequestParam("workRequestTypeId") Integer workRequestTypeId,
            Model model
    ) {
        List<WorkRequestType> requestTypeList = workRequestTypeService.getAll();
        List<WorkRequestFindJob> workerList = workRequestFindJobService.getAllByType(
                workRequestTypeId,
                Constants.STT_WORK_CODE_APPROVED
        );
        model.addAttribute("workRequestTypeId", workRequestTypeId);
        model.addAttribute("requestTypeList", requestTypeList);
        model.addAttribute("workerList", workerList);
        model.addAttribute("page", 7);
        return "module-works";
    }

    @GetMapping("/request-find-job-manage")
    public String getJobsPosted(Model model) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 2);
        List<WorkRequestFindJob> requestFindJobList = workRequestFindJobService.getAllByUserIdAndTypeId(
                2,
                sttWorkList.get(0).getSttWorkCode()
        );
        model.addAttribute("sttWorkCode", sttWorkList.get(0).getSttWorkCode());
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestFindJobList", requestFindJobList);
        model.addAttribute("page", 5);
        return "module-works";
    }

    @GetMapping("/request-find-job-manage/status")
    public String getJobsPostedStatus(
            @RequestParam("code") Integer sttWorkCode,
            Model model
    ) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 2);
        List<WorkRequestFindJob> requestFindJobList = workRequestFindJobService.getAllByUserIdAndTypeId(
                2,
                sttWorkCode
        );
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestFindJobList", requestFindJobList);
        model.addAttribute("page", 5);
        return "module-works";
    }

    @GetMapping("/request-find-job-manage/delete")
    public String getFindJobDeleteRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model
    ) {
        // code delete
        workRequestFindJobService.delete(requestId);
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 2);
        List<WorkRequestFindJob> workRequestFindJob = workRequestFindJobService.getAllByUserIdAndTypeId(
                2,
                sttWorkCode
        );
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestFindJobList", workRequestFindJob);
        model.addAttribute("page", 5);
        return "module-works";
    }

    private Map getSttCountMap(List<SttWork> sttWorkList, int userId) {
        SttWorkRequestRecruitmentFindJobCount sttCount = sttWorkRequestRecruitmentFindJobCountService.getOneByUserId(userId);

        Map<SttWork, Integer> sttWorkCountMap = new LinkedHashMap<>();
        sttWorkCountMap.put(sttWorkList.get(0), sttCount.getSttWorkRequestRecruitmentFindJobCountPending());
        sttWorkCountMap.put(sttWorkList.get(1), sttCount.getSttWorkRequestRecruitmentFindJobCountReject());
        sttWorkCountMap.put(sttWorkList.get(2), sttCount.getSttWorkRequestRecruitmentFindJobCountApproved());
        sttWorkCountMap.put(sttWorkList.get(3), sttCount.getSttWorkRequestRecruitmentFindJobCountComplete());
        sttWorkCountMap.put(sttWorkList.get(4), sttCount.getSttWorkRequestRecruitmentFindJobCountExpired());
        sttWorkCountMap.put(sttWorkList.get(5), sttCount.getSttWorkRequestRecruitmentFindJobCountSaveDraft());
        return sttWorkCountMap;
    }

    @GetMapping("/request-find-job-detail")
    public String getRequestFindJobDetail(
            @RequestParam("id") Integer id,
            Model model
    ) {
        WorkRequestFindJob jobDetail = workRequestFindJobService.getOne(id);
        WorkRequestType jobType = workRequestTypeService.getOne(jobDetail.getWorkRequestTypeId());
        WorkPaymentMethod jobPaymentMethod = workPaymentMethodService.getOne(jobDetail.getWorkPaymentMethodId());
        WorkTime jobTime = workTimeService.getOne((jobDetail.getWorkTimeId()));

        model.addAttribute("jobPaymentMethod", jobPaymentMethod);
        model.addAttribute("jobTime", jobTime);
        model.addAttribute("jobType", jobType);
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("page", 12);
        return "module-works";
    }

    @GetMapping("/create-request-find-job")
    public String getFormCreateRequestFindJob(Model model) {
        WorkRequestFindJob newRequestFindJob = WorkRequestFindJob.builder().build();
        model.addAttribute("newRequestFindJob", newRequestFindJob);
        model.addAttribute("page", 6);
        return "module-works";
    }

    @PostMapping(value = "/create-request-find-job", params = "save")
    public String createRequestFindJob(
            @ModelAttribute("newRequestFindJob") WorkRequestFindJob newRequestFindJob,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        // set attribute
        newRequestFindJob.setUserId(2);
        newRequestFindJob.setSttWorkCode(Constants.STT_WORK_CODE_PENDING_APPROVAL);
        newRequestFindJob.setWorkRequestFindJobStartDateTime(currentDate);
        newRequestFindJob.setWorkRequestFindJobLastUpdateDateTime(currentDate);
        newRequestFindJob.setWorkRequestFindJobDeleted(false);

        boolean isCheck = workRequestFindJobService.save(newRequestFindJob);
        if (isCheck) {
            return "redirect:" + "/works/request-find-job-manage";
        } else {
            return "404";
        }
    }

    @PostMapping(value = "/create-request-find-job", params = "saveDraft")
    public String createRequestFindJobSaveDraft(
            @ModelAttribute("newRequestFindJob") WorkRequestFindJob newRequestFindJob,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        // set attribute
        newRequestFindJob.setUserId(2);
        newRequestFindJob.setSttWorkCode(Constants.STT_WORK_CODE_SAVE_DRAFT);
        newRequestFindJob.setWorkRequestFindJobStartDateTime(currentDate);
        newRequestFindJob.setWorkRequestFindJobLastUpdateDateTime(currentDate);
        newRequestFindJob.setWorkRequestFindJobDeleted(false);

        boolean isCheck = workRequestFindJobService.save(newRequestFindJob);
        if (isCheck) {
            return "redirect:" + "/works/request-find-job-manage/status?code=6";
        } else {
            return "404";
        }
    }

    @GetMapping("/list-booked")
    public String getListBooked(Model model) {
        List<WorkRequestFindJob> listBooked = workRequestFindJobService.getAllListRecruitmentByUserId(2, 1);
        model.addAttribute("listBooked", listBooked);
        model.addAttribute("page", 2);
        return "module-works";
    }

    @GetMapping("/request-find-job-manage/repost-request-find-job")
    public String getFormRepostRequestFindJob(
            @RequestParam("requestFindJobId") Integer requestFindJobId,
            @RequestParam("code") Integer sttWorkCode,
            Model model
    ) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 2);
        List<WorkRequestFindJob> requestFindJobList = workRequestFindJobService.getAllByUserIdAndTypeId(
                2,
                sttWorkCode
        );
        model.addAttribute("requestFindJobId", requestFindJobId);
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestFindJobList", requestFindJobList);
        model.addAttribute("page", 5);
        return "module-works";
    }

    @PostMapping("/repost-request-find-job")
    public String repostRequestFindJob(
            @RequestParam("endDate") Date endDate,
            @RequestParam("id") Integer id
    ) {
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        WorkRequestFindJob requestFindJob = workRequestFindJobService.getOne(id);
        WorkRequestFindJob newRequestFindJob = WorkRequestFindJob.builder().build();
        newRequestFindJob.setUserId(requestFindJob.getUserId());
        newRequestFindJob.setSttWorkCode(Constants.STT_WORK_CODE_PENDING_APPROVAL);
        newRequestFindJob.setWorkRequestTypeId(requestFindJob.getWorkRequestTypeId());
        newRequestFindJob.setWorkSalaryUnitId(requestFindJob.getWorkSalaryUnitId());
        newRequestFindJob.setWorkPaymentMethodId(requestFindJob.getWorkPaymentMethodId());
        newRequestFindJob.setWorkTimeId(requestFindJob.getWorkTimeId());
        newRequestFindJob.setWorkRequestFindJobExpectedSalary(requestFindJob.getWorkRequestFindJobExpectedSalary());
        newRequestFindJob.setWorkRequestFindJobExpectedLocation(requestFindJob.getWorkRequestFindJobExpectedLocation());
        newRequestFindJob.setWorkRequestFindJobTitle(requestFindJob.getWorkRequestFindJobTitle());
        newRequestFindJob.setWorkRequestFindJobStartDateTime(currentDate);
        newRequestFindJob.setWorkRequestFindJobLastUpdateDateTime(currentDate);
        newRequestFindJob.setWorkRequestFindJobEndDateTime(endDate);
        newRequestFindJob.setWorkRequestFindJobDescription(requestFindJob.getWorkRequestFindJobDescription());
        newRequestFindJob.setWorkRequestFindJobPersonalExperience(requestFindJob.getWorkRequestFindJobPersonalExperience());
        newRequestFindJob.setWorkRequestFindJobDeleted(requestFindJob.isWorkRequestFindJobDeleted());
        boolean isCheck = workRequestFindJobService.save(newRequestFindJob);
        if (isCheck) {
            return "redirect:" + "/works/request-find-job-manage";
        } else {
            return "404";
        }
    }

    @GetMapping("/booked/show")
    public String getListUserBooked(
            @RequestParam("bookedId") Integer bookedId,
            Model model
    ) {
        List<WorkRequestFindJob> listBooked = workRequestFindJobService.getAllListRecruitmentByUserId(2, 1);
        List<UserDetail> listBookedModal = userDetailService.getAllUserBookedByUserId(bookedId);
        model.addAttribute("listBooked", listBooked);
        model.addAttribute("page", 2);
        model.addAttribute("listBookedModal", listBookedModal);
        return "module-works";
    }

    @GetMapping("/request-find-job-manage/reason-reject")
    public String getReasonRejectFindJobRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model
    ) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 2);
        List<WorkRequestFindJob> workRequestFindJobs = workRequestFindJobService.getAllByUserIdAndTypeId(
                2,
                sttWorkCode
        );
        WorkRequestFindJob requestFindJob = workRequestFindJobService.getOne(requestId);
        model.addAttribute("reasonReject", requestFindJob.getWorkRequestFindJobNote());
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestFindJobList", workRequestFindJobs);
        model.addAttribute("page", 5);
        return "module-works";
    }
}
