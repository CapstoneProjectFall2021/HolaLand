package com.hola.holalandweb.module.works.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.*;
import com.hola.holalandwork.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private final WorkRequestApplyService workRequestApplyService;
    private final WorkRequestBookService workRequestBookService;

    @Autowired
    public WorksMemberController(
            WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService,
            SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService,
            WorkRequestTypeService workRequestTypeService,
            WorkRequestFindJobService workRequestFindJobService,
            SttWorkService sttWorkService,
            WorkPaymentMethodService workPaymentMethodService,
            WorkTimeService workTimeService,
            UserDetailService userDetailService,
            WorkRequestApplyService workRequestApplyService,
            WorkRequestBookService workRequestBookService
    ) {
        this.workRequestRecruitmentSavedService = workRequestRecruitmentSavedService;
        this.sttWorkRequestRecruitmentFindJobCountService = sttWorkRequestRecruitmentFindJobCountService;
        this.workRequestTypeService = workRequestTypeService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.sttWorkService = sttWorkService;
        this.workPaymentMethodService = workPaymentMethodService;
        this.workTimeService = workTimeService;
        this.userDetailService = userDetailService;
        this.workRequestApplyService = workRequestApplyService;
        this.workRequestBookService = workRequestBookService;
    }

    @GetMapping("/jobs/save")
    public String getJobsSave(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestRecruitment> jobSaveList = workRequestRecruitmentSavedService.getAllByAccountId(currentUser.getId());
        model.addAttribute("jobSaveList", jobSaveList);
        model.addAttribute("page", 4);
        return "module-works";
    }

    @GetMapping("/jobs/save/delete")
    public String deleteJobsSaveRequest(
            @RequestParam("requestId") Integer requestId,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        boolean isCheck = workRequestRecruitmentSavedService.delete(requestId);
        if (isCheck) {
            List<WorkRequestRecruitment> jobSaveList = workRequestRecruitmentSavedService.getAllByAccountId(currentUser.getId());
            model.addAttribute("jobSaveList", jobSaveList);
            model.addAttribute("page", 4);
            return "module-works";
        } else {
            return "404";
        }
    }

    @GetMapping("/jobs/apply") // jobs/apply
    public String getJobsApply(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestRecruitment> jobApplyList = workRequestApplyService.getAllAccountId(currentUser.getId());
        model.addAttribute("jobApplyList", jobApplyList);
        model.addAttribute("page", 3);
        return "module-works";
    }

    @GetMapping("/jobs/apply/delete")
    public String deleteJobsApplyRequest(
            @RequestParam("requestId") Integer requestId,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        boolean isCheck = workRequestApplyService.delete(requestId);
        if (isCheck) {
            List<WorkRequestRecruitment> jobApplyList = workRequestApplyService.getAllAccountId(currentUser.getId());
            model.addAttribute("jobApplyList", jobApplyList);
            model.addAttribute("page", 3);
            return "module-works";
        } else {
            return "404";
        }
    }

    @GetMapping("/jobs/find/manage")
    public String getJobsPosted(Model model, Authentication authentication) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        getJobsPostedStatus(model, authentication, sttWorkList, sttWorkList.get(0).getSttWorkCode());
        return "module-works";
    }

    @GetMapping("/jobs/find/manage/status")
    public String getJobsPostedStatus(
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication
    ) {
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        getJobsPostedStatus(model, authentication, sttWorkList, sttWorkCode);
        return "module-works";
    }

    private void getJobsPostedStatus(Model model, Authentication authentication, List<SttWork> sttWorkList, int sttWorkCode) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        Map sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestFindJob> requestFindJobList = workRequestFindJobService.getAllByUserIdAndTypeId(
                currentUser.getId(),
                sttWorkCode
        );
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestFindJobList", requestFindJobList);
        model.addAttribute("page", 5);
    }

    @GetMapping("/jobs/find/manage/delete")
    public String getFindJobDeleteRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication
    ) {
        // code delete
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        workRequestFindJobService.delete(requestId);
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestFindJob> workRequestFindJob = workRequestFindJobService.getAllByUserIdAndTypeId(
                currentUser.getId(),
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
        sttWorkCountMap.put(sttWorkList.get(0), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountPending() : 0);
        sttWorkCountMap.put(sttWorkList.get(1), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountReject() : 0);
        sttWorkCountMap.put(sttWorkList.get(2), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountApproved() : 0);
        sttWorkCountMap.put(sttWorkList.get(3), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountComplete() : 0);
        sttWorkCountMap.put(sttWorkList.get(4), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountExpired() : 0);
        sttWorkCountMap.put(sttWorkList.get(5), sttCount != null ? sttCount.getSttWorkRequestRecruitmentFindJobCountSaveDraft() : 0);
        return sttWorkCountMap;
    }

    @GetMapping("/jobs/find/detail")
    public String getRequestFindJobDetail(@RequestParam("id") Integer id, Model model) {
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

    @GetMapping("/jobs/find/create")
    public String getFormCreateRequestFindJob(Model model) {
        WorkRequestFindJob newRequestFindJob = WorkRequestFindJob.builder().build();
        model.addAttribute("newRequestFindJob", newRequestFindJob);
        model.addAttribute("page", 6);
        return "module-works";
    }

    @PostMapping(value="/jobs/find/create", params="save")
    public String createRequestFindJob(
            @ModelAttribute("newRequestFindJob") WorkRequestFindJob newRequestFindJob,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        boolean isCheck = createRequestFindJobObject(newRequestFindJob, authentication, Constants.STT_WORK_CODE_PENDING_APPROVAL);
        if (isCheck) {
            return "redirect:" + "/works/jobs/find/manage";
        } else {
            return "404";
        }
    }

    @PostMapping(value="/jobs/find/create", params="saveDraft")
    public String createRequestFindJobSaveDraft(
            @ModelAttribute("newRequestFindJob") WorkRequestFindJob newRequestFindJob,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        boolean isCheck = createRequestFindJobObject(newRequestFindJob, authentication, Constants.STT_WORK_CODE_SAVE_DRAFT);
        if (isCheck) {
            return "redirect:" + "/works/jobs/find/manage/status?code=6";
        } else {
            return "404";
        }
    }

    private boolean createRequestFindJobObject(WorkRequestFindJob newRequestFindJob, Authentication authentication, int sttWorkCode) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        // set attribute
        newRequestFindJob.setUserId(currentUser.getId());
        newRequestFindJob.setSttWorkCode(sttWorkCode);
        newRequestFindJob.setWorkRequestFindJobStartDateTime(currentDate);
        newRequestFindJob.setWorkRequestFindJobLastUpdateDateTime(currentDate);
        newRequestFindJob.setWorkRequestFindJobDeleted(false);

        return workRequestFindJobService.save(newRequestFindJob);
    }

    @GetMapping("/booked")
    public String getListBooked(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestFindJob> listBooked = workRequestFindJobService.getAllListRecruitmentByUserId(
                currentUser.getId(),
                Constants.STT_WORK_CODE_WAITING_REPOSITORY
        );
        model.addAttribute("userDetailService", userDetailService);
        model.addAttribute("listBooked", listBooked);
        model.addAttribute("page", 2);
        return "module-works";
    }

    @GetMapping("/jobs/find/manage/repost")
    public String getFormRepostRequestFindJob(
            @RequestParam("requestFindJobId") Integer requestFindJobId,
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestFindJob> requestFindJobList = workRequestFindJobService.getAllByUserIdAndTypeId(
                currentUser.getId(),
                sttWorkCode
        );
        model.addAttribute("requestFindJobId", requestFindJobId);
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestFindJobList", requestFindJobList);
        model.addAttribute("page", 5);
        return "module-works";
    }

    @PostMapping("/jobs/find/manage/repost")
    public String repostRequestFindJob(
            @RequestParam("endDate") Date endDate,
            @RequestParam("id") Integer id
    ) {
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        WorkRequestFindJob requestFindJob = workRequestFindJobService.getOne(id);
        requestFindJob.setSttWorkCode(Constants.STT_WORK_CODE_PENDING_APPROVAL);
        requestFindJob.setWorkRequestFindJobStartDateTime(currentDate);
        requestFindJob.setWorkRequestFindJobLastUpdateDateTime(currentDate);
        requestFindJob.setWorkRequestFindJobEndDateTime(endDate);
        boolean isCheck = workRequestFindJobService.save(requestFindJob);
        if (isCheck) {
            return "redirect:" + "/works/jobs/find/manage";
        } else {
            return "404";
        }
    }

    @GetMapping("/booked/show")
    public String getListUserBooked(
            @RequestParam("bookedId") Integer bookedId,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestFindJob> listBooked = workRequestFindJobService.getAllListRecruitmentByUserId(
                currentUser.getId(),
                Constants.STT_WORK_CODE_WAITING_REPOSITORY
        );
        List<UserDetail> listBookedModal = userDetailService.getAllUserBookedByUserId(bookedId);
        model.addAttribute("bookedId", bookedId);
        model.addAttribute("userDetailService", userDetailService);
        model.addAttribute("listBooked", listBooked);
        model.addAttribute("page", 2);
        model.addAttribute("listBookedModal", listBookedModal);
        return "module-works";
    }

    @GetMapping("/booked/accepted")
    public String userAcceptRecruiterBooked(
            @RequestParam("bookedId") Integer bookedId,
            @RequestParam("recruiterId") Integer recruiterId,
            RedirectAttributes rm
    ) {
        WorkRequestBook requestAccepted = WorkRequestBook.builder()
                .sttWorkCode(Constants.STT_WORK_CODE_REQUEST_APPLY_BOOK_AGREED)
                .userId(recruiterId)
                .workRequestFindJobId(bookedId)
                .build();

        WorkRequestFindJob currentRequest = WorkRequestFindJob.builder()
                .sttWorkCode(Constants.STT_WORK_CODE_COMPLETE)
                .workRequestFindJobId(bookedId)
                .build();

        boolean isCheck1 = workRequestBookService.userAcceptRecruiterBooked(requestAccepted);
        boolean isCheck2 = workRequestFindJobService.updateSttRequest(currentRequest);
        if (isCheck1 && isCheck2) {
            rm.addFlashAttribute("applySuccess", true);
            return "redirect:" + "/works/jobs/find/manage/status?code=" + Constants.STT_WORK_CODE_COMPLETE;
        } else {
            return "404";
        }
    }

    @GetMapping("/booked/show/rejected")
    public String userRejectRecruiterBooked(
            @RequestParam("bookedId") Integer bookedId,
            @RequestParam("recruiterId") Integer recruiterId
    ) {
        WorkRequestBook requestReject = WorkRequestBook.builder()
                .sttWorkCode(Constants.STT_WORK_CODE_REQUEST_APPLY_BOOK_DENIED)
                .userId(recruiterId)
                .workRequestFindJobId(bookedId)
                .build();
        boolean isCheck = workRequestBookService.userRejectRecruiterBooked(requestReject);
        if (isCheck) {
            return "redirect:" + "/works/booked/show?bookedId=" + bookedId;
        } else {
            return "404";
        }
    }

    @GetMapping("/jobs/find/manage/reject/reason")
    public String getReasonRejectFindJobRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestFindJob> workRequestFindJobs = workRequestFindJobService.getAllByUserIdAndTypeId(
                currentUser.getId(),
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

    @GetMapping("/jobs/find/manage/list/booked")
    public String getListBookedFindJobRequest(
            @RequestParam("requestId") Integer requestId,
            @RequestParam("code") Integer sttWorkCode,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<SttWork> sttWorkList = sttWorkService.getAllByName(Constants.STT_WORK_NAME_RECRUITMENT_FIND_JOB);
        Map<SttWork, Integer> sttWorkCountMap = getSttCountMap(sttWorkList, currentUser.getId());
        List<WorkRequestFindJob> workRequestFindJobs = workRequestFindJobService.getAllByUserIdAndTypeId(
                currentUser.getId(),
                sttWorkCode
        );
        List<WorkRequestBook> listBooked = workRequestBookService.getAllByRequestId(requestId);
        model.addAttribute("listBooked", listBooked);
        model.addAttribute("userDetailService", userDetailService);
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestFindJobList", workRequestFindJobs);
        model.addAttribute("page", 5);
        return "module-works";
    }

}
