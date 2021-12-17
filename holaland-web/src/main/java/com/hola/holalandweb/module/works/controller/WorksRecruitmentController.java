package com.hola.holalandweb.module.works.controller;

import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.SttWork;
import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkRequestType;
import com.hola.holalandwork.service.SttWorkRequestRecruitmentFindJobCountService;
import com.hola.holalandwork.service.SttWorkService;
import com.hola.holalandwork.service.WorkRequestApplyService;
import com.hola.holalandwork.service.WorkRequestRecruitmentSavedService;
import com.hola.holalandwork.service.WorkRequestRecruitmentService;
import com.hola.holalandwork.service.WorkRequestTypeService;
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

public class WorksRecruitmentController {

    private final WorkRequestRecruitmentService workRequestRecruitmentService;
    private final WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService;
    private final SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService;
    private final WorkRequestApplyService workRequestApplyService;
    private final SttWorkService sttWorkService;
    private final WorkRequestTypeService workRequestTypeService;
    private final UserDetailService userDetailService;

    @Autowired
    public WorksRecruitmentController(WorkRequestRecruitmentService workRequestRecruitmentService,
                                      WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService,
                                      SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService,
                                      WorkRequestApplyService workRequestApplyService,
                                      SttWorkService sttWorkService,
                                      WorkRequestTypeService workRequestTypeService,
                                      UserDetailService userDetailService) {
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workRequestRecruitmentSavedService = workRequestRecruitmentSavedService;
        this.sttWorkRequestRecruitmentFindJobCountService = sttWorkRequestRecruitmentFindJobCountService;
        this.workRequestApplyService = workRequestApplyService;
        this.sttWorkService = sttWorkService;
        this.workRequestTypeService = workRequestTypeService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/jobs-apply")
    public String getJobsApply(Model model) {
        List<WorkRequestRecruitment> jobApplyList = workRequestApplyService.getAllAccountId(2);
        model.addAttribute("jobApplyList", jobApplyList);
        model.addAttribute("page", 3);
        return "module-works";
    }

    @GetMapping("/jobs-apply/delete")
    public String deleteJobsApplyRequest(@RequestParam("requestId") Integer requestId, Model model) {
        boolean isCheck = workRequestApplyService.delete(requestId);
        if (isCheck) {
            List<WorkRequestRecruitment> jobApplyList = workRequestApplyService.getAllAccountId(2);
            model.addAttribute("jobApplyList", jobApplyList);
            model.addAttribute("page", 3);
            return "module-works";
        } else {
            return "404";
        }
    }

    @GetMapping("/request-recruitment-manage")
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

    @GetMapping("/request-recruitment-manage/status")
    public String getRecruitmentsPostedStatus(
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

    @GetMapping("/request-recruitment-manage/delete")
    public String getRecruitmentDeleteRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model) {
        // code delete
        workRequestRecruitmentService.delete(requestId);
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

    @GetMapping("/request-recruitment-detail")
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

    @GetMapping("/create-request-recruitment")
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
        setAttrNewRequestRecruitment(newRequestRecruitment, 1, Constants.STT_WORK_CODE_PENDING_APPROVAL);
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
        setAttrNewRequestRecruitment(newRequestRecruitment, 1, Constants.STT_WORK_CODE_SAVE_DRAFT);
        boolean isCheck = workRequestRecruitmentService.save(newRequestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/request-recruitment-manage/status?code=6";
        } else {
            return "404";
        }
    }

    private void setAttrNewRequestRecruitment(WorkRequestRecruitment newRequestRecruitment, int userId, int sttWork) {
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

        newRequestRecruitment.setUserId(userId);
        newRequestRecruitment.setSttWorkCode(sttWork);
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
    }

    @GetMapping("/list-applied")
    public String getListApplied(Model model) {
        List<WorkRequestRecruitment> listApplied = workRequestRecruitmentService.getAllListAppliedByUserId(1, 1);
        model.addAttribute("listApplied", listApplied);
        model.addAttribute("page", 8);
        return "module-works";
    }

    @GetMapping("/request-recruitment-manage/repost-request-recruitment")
    public String getFormRepostRequestRecruitment(
            @RequestParam("requestRecruitmentId") Integer requestRecruitmentId,
            @RequestParam("code") Integer sttWorkCode,
            Model model
    ) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 1);
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                1,
                sttWorkCode
        );
        model.addAttribute("requestRecruitmentId", requestRecruitmentId);
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestRecruitmentList", workRequestRecruitments);
        model.addAttribute("page", 9);
        return "module-works";
    }

    @PostMapping("/repost-request-recruitment")
    public String repostRequestRecruitment(
            @RequestParam("endDate") Date endDate,
            @RequestParam("id") Integer id
    ) {
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        WorkRequestRecruitment requestRecruitment = workRequestRecruitmentService.getOne(id);
        WorkRequestRecruitment newRequestRecruitment = WorkRequestRecruitment.builder().build();
        newRequestRecruitment.setUserId(requestRecruitment.getUserId());
        newRequestRecruitment.setSttWorkCode(Constants.STT_WORK_CODE_PENDING_APPROVAL);
        newRequestRecruitment.setWorkRequestTypeId(requestRecruitment.getWorkRequestTypeId());
        newRequestRecruitment.setWorkSalaryUnitId(requestRecruitment.getWorkSalaryUnitId());
        newRequestRecruitment.setWorkPaymentMethodId(requestRecruitment.getWorkPaymentMethodId());
        newRequestRecruitment.setWorkRequestRecruitmentTitle(requestRecruitment.getWorkRequestRecruitmentTitle());
        newRequestRecruitment.setWorkRequestRecruitmentStartDateTime(currentDate);
        newRequestRecruitment.setWorkRequestRecruitmentEndDateTime(endDate);
        newRequestRecruitment.setWorkRequestRecruitmentLastUpdateDateTime(currentDate);
        newRequestRecruitment.setWorkRequestRecruitmentDescription(requestRecruitment.getWorkRequestRecruitmentDescription());
        newRequestRecruitment.setWorkRequestRecruitmentRequirement(requestRecruitment.getWorkRequestRecruitmentRequirement());
        newRequestRecruitment.setWorkRequestRecruitmentBenefit(requestRecruitment.getWorkRequestRecruitmentBenefit());
        newRequestRecruitment.setWorkRequestRecruitmentSalary(requestRecruitment.getWorkRequestRecruitmentSalary());
        newRequestRecruitment.setWorkRequestRecruitmentQuantity(requestRecruitment.getWorkRequestRecruitmentQuantity());
        newRequestRecruitment.setWorkRequestRecruitmentExperienceRequirement(requestRecruitment.isWorkRequestRecruitmentExperienceRequirement());
        newRequestRecruitment.setWorkRequestRecruitmentGenderRequirement(requestRecruitment.isWorkRequestRecruitmentGenderRequirement());
        newRequestRecruitment.setWorkRequestRecruitmentWorkLocation(requestRecruitment.getWorkRequestRecruitmentWorkLocation());
        newRequestRecruitment.setWorkRequestRecruitmentDeleted(requestRecruitment.isWorkRequestRecruitmentDeleted());
        boolean isCheck = workRequestRecruitmentService.save(newRequestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/request-recruitment-manage";
        } else {
            return "404";
        }
    }

    @GetMapping("/applied/show")
    public String getListUserApplied(
            @RequestParam("appliedId") Integer appliedId,
            Model model
    ) {
        List<WorkRequestRecruitment> listApplied = workRequestRecruitmentService.getAllListAppliedByUserId(1, 1);
        List<UserDetail> listAppliedModal = userDetailService.getAllUserAppliedByUserId(appliedId);
        model.addAttribute("listApplied", listApplied);
        model.addAttribute("page", 8);
        model.addAttribute("listAppliedModal", listAppliedModal);
        return "module-works";
    }

    @GetMapping("/request-recruitment-manage/reason-reject")
    public String getReasonRejectRecruitmentRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model
    ) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, 1);
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                1,
                sttWorkCode
        );
        WorkRequestRecruitment requestRecruitment = workRequestRecruitmentService.getOne(requestId);
        model.addAttribute("reasonReject", requestRecruitment.getWorkRequestRecruitmentNote());
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestRecruitmentList", workRequestRecruitments);
        model.addAttribute("page", 9);
        return "module-works";
    }
}
