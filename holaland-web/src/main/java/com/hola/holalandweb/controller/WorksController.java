package com.hola.holalandweb.controller;

import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.SttWork;
import com.hola.holalandwork.entity.WorkJobType;
import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class WorksController {

    private final WorkRequestRecruitmentService workRequestRecruitmentService;
    private final WorkJobTypeService workJobTypeService;
    private final WorkRequestApplyService workRequestApplyService;
    private final WorkJobSaveService workJobSaveService;
    private final WorkRequestFindJobService workRequestFindJobService;
    private final SttWorkService sttWorkService;

    @Autowired
    public WorksController(WorkRequestRecruitmentService workRequestRecruitmentService,
                           WorkJobTypeService workJobTypeService,
                           WorkRequestApplyService workRequestApplyService,
                           WorkJobSaveService workJobSaveService,
                           WorkRequestFindJobService workRequestFindJobService,
                           SttWorkService sttWorkService) {
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workJobTypeService = workJobTypeService;
        this.workRequestApplyService = workRequestApplyService;
        this.workJobSaveService = workJobSaveService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.sttWorkService = sttWorkService;
    }


    @GetMapping("/works")
    public String goToWorks(Model model) {
        List<WorkJobType> jobTypeList = workJobTypeService.getAll();
        List<WorkRequestRecruitment> jobList = workRequestRecruitmentService.getAllByType(jobTypeList.get(0).getWorkJobTypeId(), Constants.STT_WORK_CODE_APPROVED);
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
        List<WorkJobType> jobTypeList = workJobTypeService.getAll();
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
        List<WorkRequestRecruitment> jobSaveList = workJobSaveService.getAllByAccountId(1);
        model.addAttribute("jobSaveList", jobSaveList);
        model.addAttribute("page", 3);
        return "module-works";
    }

    @GetMapping("/works/request-manage")
    public String getJobsPosted(Model model) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        List<WorkRequestFindJob> workRequestFindJobs = workRequestFindJobService.getAllByUserIdAndTypeId(
                2,
                sttWorkList.get(0).getSttWorkCode()
        );
        model.addAttribute("sttWorkCode", Constants.STT_WORK_CODE_PENDING_APPROVAL);
        model.addAttribute("sttWorkList", sttWorkList);
        model.addAttribute("jobPostedList", workRequestFindJobs);
        model.addAttribute("page", 4);
        return "module-works";
    }

    @GetMapping("/works/request-manage/code")
    public String getJobsPostedCode(
            @RequestParam("sttWorkCode") Integer sttWorkCode,
            Model model
    ) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        List<WorkRequestFindJob> workRequestFindJobs = workRequestFindJobService.getAllByUserIdAndTypeId(
                2,
                sttWorkCode
        );
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkList", sttWorkList);
        model.addAttribute("jobPostedList", workRequestFindJobs);
        model.addAttribute("page", 4);
        return "module-works";
    }

    @GetMapping("/works/create-request-find-job")
    public String getFormCreateRequestFindJob(Model model) {
        model.addAttribute("page", 5);
        return "module-works";
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
        model.addAttribute("page", 7);
        return "module-works";
    }

    @PostMapping("/create-request-find-job")
    public String addMember(@ModelAttribute("newRequestFindJob") WorkRequestFindJob newRequestFindJob, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "error";
        }
        return "module-works";
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Timestamp.class, editor);
    }
}
