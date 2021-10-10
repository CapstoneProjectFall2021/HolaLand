package com.hola.holalandweb.controller;

import com.hola.holalandwork.entity.WorkJobType;
import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.service.WorkJobTypeService;
import com.hola.holalandwork.service.WorkRequestFindJobService;
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

    @Autowired
    public WorksController(WorkRequestRecruitmentService workRequestRecruitmentService, WorkJobTypeService workJobTypeService) {
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workJobTypeService = workJobTypeService;
    }

    @GetMapping("/works")
    public String goToWorks(Model model) {
        List<WorkJobType> jobTypes = workJobTypeService.getAll();
        List<WorkRequestRecruitment> listjobs = workRequestRecruitmentService.getAllByType(jobTypes.get(0).getWorkJobTypeId());
        model.addAttribute("jobTypes",jobTypes);
        model.addAttribute("listjobs", listjobs);
        model.addAttribute("page", 1);
        return "works";
    }

    @GetMapping("/job-detail")
    public String getJobDetail(@RequestParam("id") Integer id,
                               @RequestParam("jobTypeId") Integer jobTypeId,
                               Model model) {
        List<WorkJobType> jobTypes = workJobTypeService.getAll();
        List<WorkRequestRecruitment> listjobs = workRequestRecruitmentService.getAllByType(jobTypeId);
        WorkRequestRecruitment jobDetail = workRequestRecruitmentService.getOne(id);
        WorkJobType jobTypeDetail = workJobTypeService.getOne(jobDetail.getWorkJobTypeId());
        model.addAttribute("jobTypes",jobTypes);
        model.addAttribute("listjobs", listjobs);
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("jobTypeDetail", jobTypeDetail);
        model.addAttribute("page", 4);
        return "works";
    }
}
