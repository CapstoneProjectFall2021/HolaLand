package com.hola.holalandweb.controller;

import com.hola.holalandwork.entity.WorkJobType;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.service.WorkJobSaveService;
import com.hola.holalandwork.service.WorkJobTypeService;
import com.hola.holalandwork.service.WorkRequestApplyService;
import com.hola.holalandwork.service.WorkRequestRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WorksController {

    private final WorkRequestRecruitmentService workRequestRecruitmentService;
    private final WorkJobTypeService workJobTypeService;
    private final WorkRequestApplyService workRequestApplyService;
    private final WorkJobSaveService workJobSaveService;

    @Autowired
    public WorksController(
            WorkRequestRecruitmentService workRequestRecruitmentService,
            WorkJobTypeService workJobTypeService,
            WorkRequestApplyService workRequestApplyService,
            WorkJobSaveService workJobSaveService
    ) {
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workJobTypeService = workJobTypeService;
        this.workRequestApplyService = workRequestApplyService;
        this.workJobSaveService = workJobSaveService;
    }

    @GetMapping("/works")
    public String goToWorks(Model model) {
        List<WorkJobType> jobTypeList = workJobTypeService.getAll();
        List<WorkRequestRecruitment> jobList = workRequestRecruitmentService.getAllByType(jobTypeList.get(0).getWorkJobTypeId());
        model.addAttribute("workJobTypeId", 1);
        model.addAttribute("jobTypeList", jobTypeList);
        model.addAttribute("jobList", jobList);
        model.addAttribute("page", 1);
        return "works";
    }

    @GetMapping("/works/type")
    public String getWorkJobType(
            @RequestParam("workJobTypeId") Integer workJobTypeId,
            Model model
    ) {
        List<WorkJobType> jobTypeList = workJobTypeService.getAll();
        List<WorkRequestRecruitment> jobList = workRequestRecruitmentService.getAllByType(workJobTypeId);
        model.addAttribute("workJobTypeId", workJobTypeId);
        model.addAttribute("jobTypeList", jobTypeList);
        model.addAttribute("jobList", jobList);
        model.addAttribute("page", 1);
        return "works";
    }

    @GetMapping("/works/job-detail")
    public String getJobDetail(
            @RequestParam("id") Integer id,
            Model model
    ) {
        WorkRequestRecruitment jobDetail = workRequestRecruitmentService.getOne(id);
        WorkJobType jobType = workJobTypeService.getOne(jobDetail.getWorkJobTypeId());
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("jobType", jobType);
        model.addAttribute("page", 4);
        return "works";
    }

    @GetMapping("/works/jobs-apply")
    public String getJobsApply(Model model) {
        List<WorkRequestRecruitment> jobApplyList = workRequestApplyService.getAllAccountId(1);
        model.addAttribute("jobApplyList", jobApplyList);
        model.addAttribute("page", 2);
        return "works";
    }

    @GetMapping("/works/jobs-save")
    public String getJobsSave(Model model) {
        List<WorkRequestRecruitment> jobSaveList = workJobSaveService.getAllByAccountId(1);
        model.addAttribute("jobSaveList", jobSaveList);
        model.addAttribute("page", 3);
        return "works";
    }
}
