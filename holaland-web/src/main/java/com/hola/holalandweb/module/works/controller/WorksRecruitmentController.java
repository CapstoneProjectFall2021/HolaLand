package com.hola.holalandweb.module.works.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.*;
import com.hola.holalandwork.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private final WorkRequestFindJobService workRequestFindJobService;
    private final UserDetailService userDetailService;

    @Autowired
    public WorksRecruitmentController(
            WorkRequestRecruitmentService workRequestRecruitmentService,
            WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService,
            SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService,
            WorkRequestApplyService workRequestApplyService,
            SttWorkService sttWorkService,
            WorkRequestTypeService workRequestTypeService,
            WorkRequestFindJobService workRequestFindJobService, UserDetailService userDetailService
    ) {
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workRequestRecruitmentSavedService = workRequestRecruitmentSavedService;
        this.sttWorkRequestRecruitmentFindJobCountService = sttWorkRequestRecruitmentFindJobCountService;
        this.workRequestApplyService = workRequestApplyService;
        this.sttWorkService = sttWorkService;
        this.workRequestTypeService = workRequestTypeService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/jobs/find")
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

    @GetMapping("/jobs/find/type") // jobs/find/type
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

    @GetMapping("/jobs/recruitment/manage") // jobs/recruitment/manage
    public String getRecruitmentsPosted(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                currentUser.getId(),
                sttWorkList.get(0).getSttWorkCode()
        );
        model.addAttribute("sttWorkCode", sttWorkList.get(0).getSttWorkCode());
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestRecruitmentList", workRequestRecruitments);
        model.addAttribute("page", 9);
        return "module-works";
    }

    @GetMapping("/jobs/recruitment/manage/status") //jobs/recruitment/manage/status
    public String getRecruitmentsPostedStatus(
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                currentUser.getId(),
                sttWorkCode
        );
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestRecruitmentList", workRequestRecruitments);
        model.addAttribute("page", 9);
        return "module-works";
    }

    @GetMapping("/jobs/recruitment/manage/delete") //jobs/recruitment/manage/delete
    public String getRecruitmentDeleteRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication) {
        // code delete
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        workRequestRecruitmentService.delete(requestId);
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                currentUser.getId(),
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
        sttWorkCountMap.put(sttWorkList.get(0), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountPending() : 0);
        sttWorkCountMap.put(sttWorkList.get(1), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountReject() : 0);
        sttWorkCountMap.put(sttWorkList.get(2), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountApproved() : 0);
        sttWorkCountMap.put(sttWorkList.get(3), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountComplete() : 0);
        sttWorkCountMap.put(sttWorkList.get(4), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountExpired() : 0);
        sttWorkCountMap.put(sttWorkList.get(5), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountSaveDraft() : 0);
        return sttWorkCountMap;
    }

    @GetMapping("/jobs/recruitment/detail") // jobs/recruitment/detail
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

    @GetMapping("/jobs/recruitment/create") // jobs/recruitment/create
    public String getFormCreateRequestRecruitment(Model model) {
        WorkRequestRecruitment newRequestRecruitment = WorkRequestRecruitment.builder().build();
        model.addAttribute("newRequestRecruitment", newRequestRecruitment);
        model.addAttribute("page", 10);
        return "module-works";
    }

    @PostMapping(value = "/jobs/recruitment/create", params = "save") // jobs/recruitment/create
    public String createRequestRecruitment(
            @ModelAttribute("newRequestRecruitment") WorkRequestRecruitment newRequestRecruitment,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        setAttrNewRequestRecruitment(newRequestRecruitment, currentUser.getId(), Constants.STT_WORK_CODE_PENDING_APPROVAL);
        boolean isCheck = workRequestRecruitmentService.save(newRequestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/jobs/recruitment/manage";
        } else {
            return "404";
        }
    }

    @PostMapping(value = "/jobs/recruitment/create", params = "saveDraft") // jobs/recruitment/create
    public String createRequestRecruitmentSaveDraft(
            @ModelAttribute("newRequestRecruitment") WorkRequestRecruitment newRequestRecruitment,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        setAttrNewRequestRecruitment(newRequestRecruitment, currentUser.getId(), Constants.STT_WORK_CODE_SAVE_DRAFT);
        boolean isCheck = workRequestRecruitmentService.save(newRequestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/jobs/recruitment/manage/status?code=6";
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


    @GetMapping("/jobs/recruitment/manage/repost") // jobs/recruitment/manage/repost
    public String getFormRepostRequestRecruitment(
            @RequestParam("requestRecruitmentId") Integer requestRecruitmentId,
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                currentUser.getId(),
                sttWorkCode
        );
        model.addAttribute("requestRecruitmentId", requestRecruitmentId);
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestRecruitmentList", workRequestRecruitments);
        model.addAttribute("page", 9);
        return "module-works";
    }

    @PostMapping("/jobs/recruitment/manage/repost") // jobs/recruitment/manage/repost
    public String repostRequestRecruitment(
            @RequestParam("endDate") Date endDate,
            @RequestParam("id") Integer id
    ) {
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        WorkRequestRecruitment requestRecruitment = workRequestRecruitmentService.getOne(id);
        requestRecruitment.setSttWorkCode(Constants.STT_WORK_CODE_PENDING_APPROVAL);
        requestRecruitment.setWorkRequestRecruitmentStartDateTime(currentDate);;
        requestRecruitment.setWorkRequestRecruitmentEndDateTime(endDate);;
        requestRecruitment.setWorkRequestRecruitmentLastUpdateDateTime(currentDate);
        boolean isCheck = workRequestRecruitmentService.save(requestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/jobs/recruitment/manage";
        } else {
            return "404";
        }
    }

    @GetMapping("/apply") // apply
    public String getListApplied(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestRecruitment> listApplied = workRequestRecruitmentService.getAllListAppliedByUserId(currentUser.getId(), 1);
        model.addAttribute("listApplied", listApplied);
        model.addAttribute("userDetailService",userDetailService);
        model.addAttribute("page", 8);
        return "module-works";
    }

    @GetMapping("/aplly/show") // aplly/show
    public String getListUserApplied(
            @RequestParam("appliedId") Integer appliedId,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestRecruitment> listApplied = workRequestRecruitmentService.getAllListAppliedByUserId(currentUser.getId(), 1);
        List<UserDetail> listAppliedModal = userDetailService.getAllUserAppliedByUserId(appliedId);
        model.addAttribute("listApplied", listApplied);
        model.addAttribute("userDetailService",userDetailService);
        model.addAttribute("page", 8);
        model.addAttribute("listAppliedModal", listAppliedModal);
        return "module-works";
    }

    @GetMapping("/jobs/recruitment/manage/reject/reason") // jobs/recruitment/manage/reject/reason
    public String getReasonRejectRecruitmentRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestRecruitment> workRequestRecruitments = workRequestRecruitmentService.getAllByUserIdAndTypeId(
                currentUser.getId(),
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
