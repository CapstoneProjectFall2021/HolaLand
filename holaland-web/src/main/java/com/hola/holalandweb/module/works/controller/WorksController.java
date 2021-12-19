package com.hola.holalandweb.module.works.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.WorkPaymentMethod;
import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkRequestType;
import com.hola.holalandwork.entity.WorkTime;
import com.hola.holalandwork.service.WorkPaymentMethodService;
import com.hola.holalandwork.service.WorkRequestFindJobService;
import com.hola.holalandwork.service.WorkRequestRecruitmentService;
import com.hola.holalandwork.service.WorkRequestTypeService;
import com.hola.holalandwork.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/works")
public class WorksController {

    private final WorkRequestRecruitmentService workRequestRecruitmentService;
    private final WorkRequestTypeService workRequestTypeService;
    private final WorkRequestFindJobService workRequestFindJobService;
    private final WorkPaymentMethodService workPaymentMethodService;
    private final WorkTimeService workTimeService;

    @Autowired
    public WorksController(
            WorkRequestRecruitmentService workRequestRecruitmentService,
            WorkRequestTypeService workRequestTypeService,
            WorkRequestFindJobService workRequestFindJobService,
            WorkPaymentMethodService workPaymentMethodService,
            WorkTimeService workTimeService
    ) {
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workRequestTypeService = workRequestTypeService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.workPaymentMethodService = workPaymentMethodService;
        this.workTimeService = workTimeService;
    }

    @GetMapping("")
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

    @GetMapping("/type")
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

    @GetMapping("/jobs/recruitment/detail")
    public String getRequestRecruitmentDetail(
            @RequestParam("id") Integer id,
            Model model,
            Authentication authentication
    ) {
        if (authentication != null) {
            CustomUser currentUser = (CustomUser) authentication.getPrincipal();
            // check request này đã được user hiện tại save hay chưa
            model.addAttribute("saved", true);

            // check request này đã được user hiện tại ứng tuyển hay chưa
            model.addAttribute("apply", true);
        }

        WorkRequestRecruitment jobDetail = workRequestRecruitmentService.getOne(id);
        WorkRequestType jobType = workRequestTypeService.getOne(jobDetail.getWorkRequestTypeId());

        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("jobType", jobType);
        model.addAttribute("page", 11);
        return "module-works";
    }

    // need login
    @GetMapping("/jobs/find/detail")
    public String getRequestFindJobDetail(@RequestParam("id") Integer id, Model model) {
        WorkRequestFindJob jobDetail = workRequestFindJobService.getOne(id);
        WorkRequestType jobType = workRequestTypeService.getOne(jobDetail.getWorkRequestTypeId());
        WorkPaymentMethod jobPaymentMethod = workPaymentMethodService.getOne(jobDetail.getWorkPaymentMethodId());
        WorkTime jobTime = workTimeService.getOne((jobDetail.getWorkTimeId()));

        // check request này đã được nhà tuyển dụng hiện tại thuê hay chưa
        model.addAttribute("rent", true);

        model.addAttribute("jobPaymentMethod", jobPaymentMethod);
        model.addAttribute("jobTime", jobTime);
        model.addAttribute("jobType", jobType);
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("page", 12);
        return "module-works";
    }
}
