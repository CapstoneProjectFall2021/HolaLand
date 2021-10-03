package com.hola.holalandweb.controller;

import com.hola.holalandcore.service.AccountService;
import com.hola.holalandfptu.entity.Club;
import com.hola.holalandfptu.entity.ClubType;
import com.hola.holalandfptu.service.ClubService;
import com.hola.holalandfptu.service.ClubTypeService;
import com.hola.holalandtraffic.entity.Member;
import com.hola.holalandtraffic.service.BusService;
import com.hola.holalandtraffic.service.MemberService;
import com.hola.holalandtraffic.service.MotorbikeTaxiDriversService;
import com.hola.holalandweb.util.SendEmailService;
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

    // call MemberService from module traffic
    private final MemberService memberService;
    // call AccountService from module core
    private final AccountService accountService;
    private final SendEmailService sendEmailService;
    private final BusService busService;
    private final ClubService clubService;
    private final ClubTypeService clubTypeService;

    @Autowired
    public MemberController(MemberService memberService, AccountService accountService, SendEmailService sendEmailService, BusService busService, MotorbikeTaxiDriversService motorbikeTaxiDriversService,
                            ClubService clubService, ClubTypeService clubTypeService) {
        this.memberService = memberService;
        this.accountService = accountService;
        this.sendEmailService = sendEmailService;
        this.busService = busService;
        this.clubService = clubService;
        this.clubTypeService = clubTypeService;
    }

    @GetMapping("/members")
    public String members(Model model) {
        backToMembers(model);
        List listBus = busService.getAll();
        List listClub = clubService.getAllByType(1);
        List listCLubType = clubTypeService.getAll();

        model.addAttribute("listBus", listBus);
        model.addAttribute("listClub", listClub);
        model.addAttribute("listClubType", listCLubType);
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
        backToMembers(model);
        return "members";
    }

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
            backToMembers(model);
            return "members";
        } else {
            return "error";
        }
    }

    @GetMapping("confirm-delete-member")
    public String deleteMember(@RequestParam("id") Integer id, Model model) {
        memberService.delete(id);
        backToMembers(model);
        return "members";
    }

    private void backToMembers(Model model) {
        List<Member> members = memberService.getAll();
        model.addAttribute("addMember", Member.builder().build());
        model.addAttribute("members", members);
    }
// demo fptu club
@GetMapping("/fpt-university")
public String goToFptUniversity(Model model) {
    model.addAttribute("page", 1);
    return "fpt-university";
}

    @GetMapping("/fpt-university-club")
    public String goToFptUniversityClub(Model model) {
        List<ClubType> clubTypeList = clubTypeService.getAll();
        List<Club> clubList = clubService.getAllByType(clubTypeList.get(0).getFptuClubTypeId());
        model.addAttribute("clubTypeList", clubTypeList);
        model.addAttribute("clubList", clubList);
        model.addAttribute("page", 2);
        return "fpt-university";
    }

    @GetMapping("/fpt-university-club/type")
    public String getFptUniversityClubType(
            @RequestParam("clubTypeId") Integer clubTypeId,
            @RequestParam("page") Integer page,
            Model model
    ) {
        List<ClubType> clubTypeList = clubTypeService.getAll();
        List<Club> clubList = clubService.getAllByType(clubTypeId);
        model.addAttribute("clubTypeList", clubTypeList);
        model.addAttribute("clubList", clubList);
        model.addAttribute("page", page);
        return "fpt-university";
    }
    @GetMapping("/fpt-university-club/detail")
    public String getFptUniversityClubDetail(
            @RequestParam("clubTypeId") Integer clubTypeId,
            @RequestParam("clubId") Integer clubId,
            @RequestParam("page") Integer page,
            Model model
    ) {
        List<ClubType> clubTypeList = clubTypeService.getAll();
        List<Club> clubList = clubService.getAllByType(clubTypeId);
        Club club = clubService.getOne(clubId);
        model.addAttribute("clubTypeList", clubTypeList);
        model.addAttribute("clubList", clubList);
        model.addAttribute("club", club);
        model.addAttribute("page", page);
        return "fpt-university";
    }
}
