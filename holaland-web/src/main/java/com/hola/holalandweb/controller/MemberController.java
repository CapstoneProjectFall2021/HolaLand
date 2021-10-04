package com.hola.holalandweb.controller;

import com.hola.holalandtraffic.entity.Member;
import com.hola.holalandtraffic.service.MemberService;
import com.hola.holalandweb.util.SendEmailService;
import com.hola.holalandwork.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final SendEmailService sendEmailService;
    //call WorkService from module holaland-work
    private final SttWorkReportService sttWorkReportService;
    private final SttWorkRequestFindJobService sttWorkRequestFindJobService;
    private final SttWorkRequestRecruitmentService sttWorkRequestRecruitmentService;
    private final WorkCommentService workCommentService;
    private final WorkJobTypeService  workJobTypeService;
    private final WorkReportService workReportService;
    private final WorkRequestApplyService workRequestApplyService;
    private final WorkRequestBookService workRequestBookService;
    private final WorkRequestFindJobService workRequestFindJobService;
    private final WorkRequestRecruitmentService workRequestRecruitmentService;
    private final WorkSalaryTypeService workSalaryTypeService;
    private final WorkTimeService workTimeService;
    @Autowired
    public MemberController(MemberService memberService, SendEmailService sendEmailService, SttWorkReportService sttWorkReportService, SttWorkRequestFindJobService
            sttWorkRequestFindJobService, SttWorkRequestRecruitmentService sttWorkRequestRecruitmentService,
                            WorkCommentService workCommentService, WorkJobTypeService workJobTypeService,
                            WorkReportService workReportService, WorkRequestApplyService workRequestApplyService,
                            WorkRequestBookService workRequestBookService, WorkRequestFindJobService
                                        workRequestFindJobService, WorkRequestRecruitmentService
                                        workRequestRecruitmentService, WorkSalaryTypeService workSalaryTypeService,
                            WorkTimeService workTimeService) {
        this.memberService = memberService;
        this.sendEmailService = sendEmailService;
        this.sttWorkReportService = sttWorkReportService;
        this.sttWorkRequestFindJobService = sttWorkRequestFindJobService;
        this.sttWorkRequestRecruitmentService = sttWorkRequestRecruitmentService;
        this.workCommentService = workCommentService;
        this.workJobTypeService = workJobTypeService;
        this.workReportService = workReportService;
        this.workRequestApplyService = workRequestApplyService;
        this.workRequestBookService = workRequestBookService;
        this.workRequestFindJobService = workRequestFindJobService;
        this.workRequestRecruitmentService = workRequestRecruitmentService;
        this.workSalaryTypeService = workSalaryTypeService;
        this.workTimeService = workTimeService;
    }

    @GetMapping("/members")
    public String members(Model model) {
        List listSttWorkReportService = sttWorkReportService.getAll();
        List listSttWorkRequestFindJobService = sttWorkRequestFindJobService.getAll();
        List listSttWorkRequestRecruitmentService = sttWorkRequestRecruitmentService.getAll();
        List listWorkCommentService = workCommentService.getAll();
        List listWorkJobTypeService = workJobTypeService.getAll();
        List listWorkReportService = workReportService.getAll();
        List listWorkRequestApplyService = workRequestApplyService.getAll();
        List listWorkRequestBookService = workRequestBookService.getAll();
        List listWorkRequestFindJobService = workRequestFindJobService.getAll();
        List listWorkRequestRecruitmentService = workRequestRecruitmentService.getAll();
        List listWorkSalaryTypeService = workSalaryTypeService.getAll();
        List listWorkTimeService = workTimeService.getAll();

        model.addAttribute("listSttWorkReport", listSttWorkReportService);
        model.addAttribute("listSttWorkRequestFindJob", listSttWorkRequestFindJobService);
        model.addAttribute("listSttWorkRequestRecruitment", listSttWorkRequestRecruitmentService);
        model.addAttribute("listWorkComment", listWorkCommentService);
        model.addAttribute("listWorkJobType", listWorkJobTypeService);
        model.addAttribute("listWorkReport", listWorkReportService);
        model.addAttribute("listWorkRequestApply", listWorkRequestApplyService);
        model.addAttribute("listWorkRequestBook", listWorkRequestBookService);
        model.addAttribute("listWorkRequestFindJob", listWorkRequestFindJobService);
        model.addAttribute("listWorkRequestRecruitment", listWorkRequestRecruitmentService);
        model.addAttribute("listWorkSalaryType", listWorkSalaryTypeService);
        model.addAttribute("listWorkTime", listWorkTimeService);

        return "members";
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam("id") Integer id, Model model) {
        Member member = memberService.getOne(id);
        sendEmailService.send(
                "HolaLand",
                "Mã kích hoạt của bạn là: 123456xxx",
                member.getMemberEmail()
        );
        model.addAttribute("send", "- Gửi email thành công!");
        return "members";
    }
    //add member
    @PostMapping("/add-member")
    public String addMember(@ModelAttribute("addMember") Member addMember, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "error";
        }
        addMember.setMemberRankId(1);
        addMember.setMemberStatusId(1);

        int memberId = memberService.save(addMember);
        if (memberId > 0) {
            return "members";
        } else {
            return "error";
        }
    }

    @GetMapping("/confirm-delete-member")
    public String deleteMember(@RequestParam("id") Integer id, Model model) {
        memberService.delete(id);
        return "members";
    }



}
