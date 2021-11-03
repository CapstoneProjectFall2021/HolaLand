package com.hola.holalandweb.controller;

import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.SttWork;
import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import com.hola.holalandwork.entity.WorkPaymentMethod;
import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkRequestType;
import com.hola.holalandwork.entity.WorkTime;
import com.hola.holalandwork.service.SttWorkRequestRecruitmentFindJobCountService;
import com.hola.holalandwork.service.SttWorkService;
import com.hola.holalandwork.service.WorkPaymentMethodService;
import com.hola.holalandwork.service.WorkRequestApplyService;
import com.hola.holalandwork.service.WorkRequestFindJobService;
import com.hola.holalandwork.service.WorkRequestRecruitmentSavedService;
import com.hola.holalandwork.service.WorkRequestRecruitmentService;
import com.hola.holalandwork.service.WorkRequestTypeService;
import com.hola.holalandwork.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WorksController {

    private final WorkRequestRecruitmentService workRequestRecruitmentService;
    private final WorkRequestTypeService workRequestTypeService;
    private final WorkRequestApplyService workRequestApplyService;
    private final WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService;
    private final WorkRequestFindJobService workRequestFindJobService;
    private final WorkPaymentMethodService workPaymentMethodService;
    private final WorkTimeService workTimeService;
    private final SttWorkService sttWorkService;
    private final SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService;

    @Autowired
    public WorksController(WorkRequestRecruitmentService workRequestRecruitmentService,
                           WorkRequestTypeService workRequestTypeService,
                           WorkRequestApplyService workRequestApplyService,
                           WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService,
                           WorkRequestFindJobService workRequestFindJobService,
                           WorkPaymentMethodService workPaymentMethodService,
                           WorkTimeService workTimeService,
                           SttWorkService sttWorkService,
                           SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService) {
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workRequestTypeService = workRequestTypeService;
        this.workRequestApplyService = workRequestApplyService;
        this.workRequestRecruitmentSavedService = workRequestRecruitmentSavedService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.workPaymentMethodService = workPaymentMethodService;
        this.workTimeService = workTimeService;
        this.sttWorkService = sttWorkService;
        this.sttWorkRequestRecruitmentFindJobCountService = sttWorkRequestRecruitmentFindJobCountService;
    }


    @GetMapping("/works")
    public String goToWorks(Model model) {
        List<WorkRequestType> jobTypeList = workRequestTypeService.getAll();
        List<WorkRequestRecruitment> jobList = workRequestRecruitmentService.getAllByType(
                jobTypeList.get(0).getWorkRequestTypeId(),
                Constants.STT_WORK_CODE_APPROVED
        );
        model.addAttribute("workJobTypeId", 1);
        model.addAttribute("jobTypeList", jobTypeList);
        model.addAttribute("jobList", jobList);
        model.addAttribute("page", 1);
        return "module-works";
    }

    @GetMapping("/works/type")
    public String getWorkJobType(
            @RequestParam("workJobTypeId") Integer workJobTypeId,
            Model model
    ) {
        List<WorkRequestType> jobTypeList = workRequestTypeService.getAll();
        List<WorkRequestRecruitment> jobList = workRequestRecruitmentService.getAllByType(workJobTypeId, Constants.STT_WORK_CODE_APPROVED);
        model.addAttribute("workJobTypeId", workJobTypeId);
        model.addAttribute("jobTypeList", jobTypeList);
        model.addAttribute("jobList", jobList);
        model.addAttribute("page", 1);
        return "module-works";
    }

    @GetMapping("/works/jobs-apply")
    public String getJobsApply(Model model) {
        List<WorkRequestRecruitment> jobApplyList = workRequestApplyService.getAllAccountId(1);
        model.addAttribute("jobApplyList", jobApplyList);
        model.addAttribute("page", 2);
        return "module-works";
    }

    @GetMapping("/works/jobs-save")
    public String getJobsSave(Model model) {
        List<WorkRequestRecruitment> jobSaveList = workRequestRecruitmentSavedService.getAllByAccountId(1);
        model.addAttribute("jobSaveList", jobSaveList);
        model.addAttribute("page", 4);
        return "module-works";
    }

    @GetMapping("/works/request-find-job")
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

    @GetMapping("/works/request-find-job/type")
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

    @GetMapping("/works/request-find-job-manage")
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

    @GetMapping("/works/request-find-job-manage/status")
    public String getJobsPostedCode(
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

    @GetMapping("/works/request-recruitment-manage")
    public String getRecruitmentsPosted(Model model) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 1);
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                1,
                sttWorkList.get(0).getSttWorkCode()
        );
        model.addAttribute("sttWorkCode", sttWorkList.get(0).getSttWorkCode());
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestRecruitmentList", workRequestRecruitments);
        model.addAttribute("page", 9);
        return "module-works";
    }

    @GetMapping("/works/request-recruitment-manage/status")
    public String getRecruitmentsPostedCode(
            @RequestParam("code") Integer sttWorkCode,
            Model model
    ) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 1);
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                1,
                sttWorkCode
        );
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestRecruitmentList", workRequestRecruitments);
        model.addAttribute("page", 9);
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

    @GetMapping("/works/request-recruitment-detail")
    public String getRequestRecruitmentDetail(
            @RequestParam("id") Integer id,
            Model model
    ) {
        WorkRequestRecruitment jobDetail = workRequestRecruitmentService.getOne(id);
        WorkRequestType jobType = workRequestTypeService.getOne(jobDetail.getWorkRequestTypeId());
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("jobType", jobType);
        model.addAttribute("page", 11);
        return "module-works";
    }

    @GetMapping("/works/request-find-job-detail")
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

    @GetMapping("/works/create-request-find-job")
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

    @GetMapping("/works/create-request-recruitment")
    public String getFormCreateRequestRecruitment(Model model) {
        WorkRequestRecruitment newRequestRecruitment = WorkRequestRecruitment.builder().build();
        model.addAttribute("newRequestRecruitment", newRequestRecruitment);
        model.addAttribute("page", 10);
        return "module-works";
    }

    @PostMapping(value = "/create-request-recruitment", params = "save")
    public String createRequestRecruitment(
            @ModelAttribute("newRequestRecruitment") WorkRequestRecruitment newRequestRecruitment,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        // set attribute
        newRequestRecruitment.setUserId(1);
        newRequestRecruitment.setSttWorkCode(Constants.STT_WORK_CODE_PENDING_APPROVAL);
        newRequestRecruitment.setWorkRequestRecruitmentStartDateTime(currentDate);
        newRequestRecruitment.setWorkRequestRecruitmentLastUpdateDateTime(currentDate);
        newRequestRecruitment.setWorkRequestRecruitmentDeleted(false);

        if (newRequestRecruitment.getWorkSalaryUnitId() == 1) {
            newRequestRecruitment.setWorkRequestRecruitmentSalary(newRequestRecruitment.getWorkRequestRecruitmentSalary() + " VNĐ/h");
        } else if (newRequestRecruitment.getWorkSalaryUnitId() == 2) {
            newRequestRecruitment.setWorkRequestRecruitmentSalary(newRequestRecruitment.getWorkRequestRecruitmentSalary() + " VNĐ/ngày");
        } else if (newRequestRecruitment.getWorkSalaryUnitId() == 3) {
            newRequestRecruitment.setWorkRequestRecruitmentSalary(newRequestRecruitment.getWorkRequestRecruitmentSalary() + " VNĐ/tuần");
        } else {
            newRequestRecruitment.setWorkRequestRecruitmentSalary(newRequestRecruitment.getWorkRequestRecruitmentSalary() + " VNĐ/tháng");
        }

        boolean isCheck = workRequestRecruitmentService.save(newRequestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/request-recruitment-manage";
        } else {
            return "404";
        }
    }

    @PostMapping(value = "/create-request-recruitment", params = "saveDraft")
    public String createRequestRecruitmentSaveDraft(
            @ModelAttribute("newRequestRecruitment") WorkRequestRecruitment newRequestRecruitment,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        // set attribute
        newRequestRecruitment.setUserId(1);
        newRequestRecruitment.setSttWorkCode(Constants.STT_WORK_CODE_SAVE_DRAFT);
        newRequestRecruitment.setWorkRequestRecruitmentStartDateTime(currentDate);
        newRequestRecruitment.setWorkRequestRecruitmentLastUpdateDateTime(currentDate);
        newRequestRecruitment.setWorkRequestRecruitmentDeleted(false);

        if (newRequestRecruitment.getWorkSalaryUnitId() == 1) {
            newRequestRecruitment.setWorkRequestRecruitmentSalary(newRequestRecruitment.getWorkRequestRecruitmentSalary() + " VNĐ/h");
        } else if (newRequestRecruitment.getWorkSalaryUnitId() == 2) {
            newRequestRecruitment.setWorkRequestRecruitmentSalary(newRequestRecruitment.getWorkRequestRecruitmentSalary() + " VNĐ/ngày");
        } else if (newRequestRecruitment.getWorkSalaryUnitId() == 3) {
            newRequestRecruitment.setWorkRequestRecruitmentSalary(newRequestRecruitment.getWorkRequestRecruitmentSalary() + " VNĐ/tuần");
        } else {
            newRequestRecruitment.setWorkRequestRecruitmentSalary(newRequestRecruitment.getWorkRequestRecruitmentSalary() + " VNĐ/tháng");
        }

        boolean isCheck = workRequestRecruitmentService.save(newRequestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/request-recruiment-manage/status?code=6";
        } else {
            return "404";
        }
    }

    @GetMapping("works/list-applied")
    public String getListApplied(Model model) {
        List<WorkRequestRecruitment> listApplied = workRequestRecruitmentService.getAllListAppliedByUserId(1, Constants.STT_WORK_CODE_APPROVED);
        model.addAttribute("listApplied", listApplied);
        model.addAttribute("page", 8);
        return "module-works";
    }
}
