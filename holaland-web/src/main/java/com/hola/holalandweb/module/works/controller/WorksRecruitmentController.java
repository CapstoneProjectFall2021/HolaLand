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
import com.hola.holalandwork.entity.WorkRequestType;
import com.hola.holalandwork.service.SttWorkRequestRecruitmentFindJobCountService;
import com.hola.holalandwork.service.SttWorkService;
import com.hola.holalandwork.service.WorkRequestApplyService;
import com.hola.holalandwork.service.WorkRequestBookService;
import com.hola.holalandwork.service.WorkRequestFindJobService;
import com.hola.holalandwork.service.WorkRequestRecruitmentService;
import com.hola.holalandwork.service.WorkRequestTypeService;
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
public class WorksRecruitmentController {

    private final WorkRequestRecruitmentService workRequestRecruitmentService;
    private final SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService;
    private final WorkRequestApplyService workRequestApplyService;
    private final SttWorkService sttWorkService;
    private final WorkRequestTypeService workRequestTypeService;
    private final WorkRequestFindJobService workRequestFindJobService;
    private final UserDetailService userDetailService;
    private final WorkRequestBookService workRequestBookService;

    @Autowired
    public WorksRecruitmentController(
            WorkRequestRecruitmentService workRequestRecruitmentService,
            SttWorkRequestRecruitmentFindJobCountService sttWorkRequestRecruitmentFindJobCountService,
            WorkRequestApplyService workRequestApplyService,
            SttWorkService sttWorkService,
            WorkRequestTypeService workRequestTypeService,
            WorkRequestFindJobService workRequestFindJobService,
            UserDetailService userDetailService,
            WorkRequestBookService workRequestBookService) {
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.sttWorkRequestRecruitmentFindJobCountService = sttWorkRequestRecruitmentFindJobCountService;
        this.workRequestApplyService = workRequestApplyService;
        this.sttWorkService = sttWorkService;
        this.workRequestTypeService = workRequestTypeService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.userDetailService = userDetailService;
        this.workRequestBookService = workRequestBookService;
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

    // thuê người dùng
    @GetMapping("/jobs/book")
    public ResponseEntity<?> bookUser(@RequestParam("requestId") Integer requestId, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        WorkRequestBook workRequestBook = WorkRequestBook.builder()
                .userId(currentUser.getId())
                .sttWorkCode(Constants.STT_WORK_CODE_WAITING_REPOSITORY)
                .workRequestFindJobId(requestId)
                .workRequestBookDeleted(false)
                .build();

        boolean isSuccess = workRequestBookService.save(workRequestBook);
        if (isSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        newRequestRecruitment.setWorkRequestRecruitmentNote(null);
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

    @GetMapping("/apply/show") // apply/show
    public String getListUserApplied(
            @RequestParam("appliedId") Integer appliedId,
            Model model,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<WorkRequestRecruitment> listApplied = workRequestRecruitmentService.getAllListAppliedByUserId(currentUser.getId(), 1);
        List<UserDetail> listAppliedModal = userDetailService.getAllUserAppliedByUserId(appliedId);
        model.addAttribute("appliedId", appliedId);
        model.addAttribute("listApplied", listApplied);
        model.addAttribute("userDetailService",userDetailService);
        model.addAttribute("format", new Format());
        model.addAttribute("page", 8);
        model.addAttribute("listAppliedModal", listAppliedModal);
        return "module-works";
    }

    @GetMapping("/apply/show/accepted")
    public String recruiterAcceptUserApply(
            @RequestParam("appliedId") Integer appliedId,
            @RequestParam("userId") Integer userId,
            RedirectAttributes rm
    ) {
        WorkRequestApply requestReject = WorkRequestApply.builder()
                .sttWorkCode(Constants.STT_WORK_CODE_REQUEST_APPLY_BOOK_AGREED)
                .userId(userId)
                .workRequestRecruitmentId(appliedId)
                .build();
        WorkRequestRecruitment currentRequest = WorkRequestRecruitment.builder()
                .sttWorkCode(Constants.STT_WORK_CODE_COMPLETE)
                .workRequestRecruitmentId(appliedId)
                .build();

        boolean isCheck1 = workRequestApplyService.recruiterAcceptUserApply(requestReject);
        boolean isCheck2 = workRequestRecruitmentService.updateSttRequest(currentRequest);
        if(isCheck1 && isCheck2) {
            rm.addFlashAttribute("applySuccess", true);
            return "redirect:" + "/works/jobs/recruitment/manage/status?code="+Constants.STT_WORK_CODE_COMPLETE;
        }else {
            return "404";
        }
    }

    @GetMapping("/apply/show/rejected")
    public String recruiterRejectUserApply(
            @RequestParam("appliedId") Integer appliedId,
            @RequestParam("userId") Integer userId
    ) {
        WorkRequestApply requestReject = WorkRequestApply.builder()
                .sttWorkCode(Constants.STT_WORK_CODE_REQUEST_APPLY_BOOK_DENIED)
                .userId(userId)
                .workRequestRecruitmentId(appliedId)
                .build();
        boolean isCheck = workRequestApplyService.recruiterRejectUserApply(requestReject);
        if(isCheck) {
            return "redirect:" + "/works/apply/show?appliedId="+appliedId;
        }else {
            return "404";
        }
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

    @GetMapping("/jobs/recruitment/manage/list/apply")
    public String getListBookedFindJobRequest(
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
        List<WorkRequestApply> listApply = workRequestApplyService.getAllByRequestId(requestId);
        model.addAttribute("listApply", listApply);
        model.addAttribute("userDetailService", userDetailService);
        model.addAttribute("sttWorkCode", sttWorkCode);
        model.addAttribute("sttWorkCountMap", sttWorkCountMap);
        model.addAttribute("requestRecruitmentList", workRequestRecruitments);
        model.addAttribute("page", 9);
        return "module-works";
    }

    @GetMapping("/jobs/recruitment/edit")
    public String getFormUpdateRequestRecruitment(
            @RequestParam("requestId") int requestId, @RequestParam("code") int code, Model model
    ) {
        WorkRequestRecruitment requestRecruitment = workRequestRecruitmentService.getOne(requestId);
        model.addAttribute("requestRecruitment", requestRecruitment);
        model.addAttribute("code", code);
        model.addAttribute("page", 13);
        return "module-works";
    }

    @PostMapping(value = "/jobs/recruitment/edit", params = "save")
    public String updateRequestRecruitment(
            @ModelAttribute("requestRecruitment") WorkRequestRecruitment requestRecruitment,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        setAttrNewRequestRecruitment(requestRecruitment, currentUser.getId(), Constants.STT_WORK_CODE_PENDING_APPROVAL);
        boolean isCheck = workRequestRecruitmentService.update(requestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/jobs/recruitment/manage";
        } else {
            return "404";
        }
    }

    @PostMapping(value = "/jobs/recruitment/edit", params = "saveDraft")
    public String updateRequestRecruitmentSaveDraft(
            @ModelAttribute("requestRecruitment") WorkRequestRecruitment requestRecruitment,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        setAttrNewRequestRecruitment(requestRecruitment, currentUser.getId(), Constants.STT_WORK_CODE_SAVE_DRAFT);
        boolean isCheck = workRequestRecruitmentService.update(requestRecruitment);
        if (isCheck) {
            return "redirect:" + "/works/jobs/recruitment/manage/status?code=6";
        } else {
            return "404";
        }
    }
}
