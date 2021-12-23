package com.hola.holalandweb.module.works.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandcore.util.Format;
import com.hola.holalandweb.constant.Constants;
import com.hola.holalandwork.entity.SttWork;
import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestBook;
import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkRequestRecruitmentSaved;
import com.hola.holalandwork.service.SttWorkRequestRecruitmentFindJobCountService;
import com.hola.holalandwork.service.SttWorkService;
import com.hola.holalandwork.service.WorkRequestApplyService;
import com.hola.holalandwork.service.WorkRequestBookService;
import com.hola.holalandwork.service.WorkRequestFindJobService;
import com.hola.holalandwork.service.WorkRequestRecruitmentSavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final WorkRequestFindJobService workRequestFindJobService;
    private final SttWorkService sttWorkService;
    private final UserDetailService userDetailService;
    private final WorkRequestApplyService workRequestApplyService;
    private final WorkRequestBookService workRequestBookService;

    @Autowired
    public WorksMemberController(
            WorkRequestRecruitmentSavedService workRequestRecruitmentSavedService,
            SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService,
            WorkRequestFindJobService workRequestFindJobService,
            SttWorkService sttWorkService,
            UserDetailService userDetailService,
            WorkRequestApplyService workRequestApplyService,
            WorkRequestBookService workRequestBookService
    ) {
        this.workRequestRecruitmentSavedService = workRequestRecruitmentSavedService;
        this.sttWorkRequestRecruitmentFindJobCountService = sttWorkRequestRecruitmentFindJobCountService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.sttWorkService = sttWorkService;
        this.userDetailService = userDetailService;
        this.workRequestApplyService = workRequestApplyService;
        this.workRequestBookService = workRequestBookService;
    }

    @GetMapping("/jobs/saved")
    public String getJobsSave(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestRecruitment> jobSaveList = workRequestRecruitmentSavedService.getAllByAccountId(currentUser.getId());
        model.addAttribute("jobSaveList", jobSaveList);
        model.addAttribute("page", 4);
        return "module-works";
    }

    @GetMapping("/jobs/saved/delete")
    public String deleteJobsSave(
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

    @GetMapping("/jobs/applied")
    public String getJobsApply(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestRecruitment> jobApplyList = workRequestApplyService.getAllAccountId(currentUser.getId());
        model.addAttribute("jobApplyList", jobApplyList);
        model.addAttribute("page", 3);
        return "module-works";
    }

    @GetMapping("/jobs/applied/delete")
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

    @GetMapping("/jobs/apply")
    public ResponseEntity<?> applyJob(@RequestParam("requestId") Integer requestId, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        WorkRequestApply workRequestApply = WorkRequestApply.builder()
                .userId(currentUser.getId())
                .workRequestRecruitmentId(requestId)
                .sttWorkCode(Constants.STT_WORK_CODE_WAITING_REPOSITORY)
                .workRequestApplyDeleted(false)
                .build();

        boolean isSuccess = workRequestApplyService.save(workRequestApply);
        if (isSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/jobs/save")
    public ResponseEntity<?> saveJob(@RequestParam("requestId") Integer requestId, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        WorkRequestRecruitmentSaved workRequestRecruitmentSaved = WorkRequestRecruitmentSaved.builder()
                .userId(currentUser.getId())
                .workRequestRecruitmentId(requestId)
                .workRequestRecruitmentSavedDeleted(false)
                .build();

        boolean isSuccess = workRequestRecruitmentSavedService.save(workRequestRecruitmentSaved);
        if (isSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        model.addAttribute("format", new Format());
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
            rm.addFlashAttribute("bookedSuccess", true);
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

    @GetMapping("/jobs/find/edit")
    public String getFormUpdateRequestFindJob(
            @RequestParam("requestId") int requestId, @RequestParam("code") int code, Model model
    ) {
        WorkRequestFindJob requestFindJob = workRequestFindJobService.getOne(requestId);
        model.addAttribute("requestFindJob", requestFindJob);
        model.addAttribute("code", code);
        model.addAttribute("page", 14);
        return "module-works";
    }

    @PostMapping(value="/jobs/find/edit", params="save")
    public String updateRequestFindJob(
            @ModelAttribute("requestFindJob") WorkRequestFindJob requestFindJob,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        boolean isCheck = updateRequestFindJobObject(requestFindJob, Constants.STT_WORK_CODE_PENDING_APPROVAL);
        if (isCheck) {
            return "redirect:" + "/works/jobs/find/manage";
        } else {
            return "404";
        }
    }

    @PostMapping(value="/jobs/find/edit", params="saveDraft")
    public String updateRequestFindJobSaveDraft(
            @ModelAttribute("requestFindJob") WorkRequestFindJob requestFindJob,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }

        boolean isCheck = updateRequestFindJobObject(requestFindJob, Constants.STT_WORK_CODE_SAVE_DRAFT);
        if (isCheck) {
            return "redirect:" + "/works/jobs/find/manage/status?code=6";
        } else {
            return "404";
        }
    }

    private boolean updateRequestFindJobObject(WorkRequestFindJob requestFindJob, int sttWorkCode) {
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        // set attribute
        requestFindJob.setSttWorkCode(sttWorkCode);
        requestFindJob.setWorkRequestFindJobLastUpdateDateTime(currentDate);
        requestFindJob.setWorkRequestFindJobNote(null);
        requestFindJob.setWorkRequestFindJobDeleted(false);

        return workRequestFindJobService.update(requestFindJob);
    }
}
