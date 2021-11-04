package com.hola.holalandweb.controller;

import com.hola.holalandfptu.service.ClubService;
import com.hola.holalandfptu.service.ClubTypeService;
import com.hola.holalandtraffic.entity.Member;
import com.hola.holalandtraffic.service.BusService;
import com.hola.holalandtraffic.service.MemberService;
import com.hola.holalandtraffic.service.MotorbikeTaxiDriversService;
import com.hola.holalandweb.util.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class MemberController {

    // call MemberService from module traffic
    private final MemberService memberService;
    // call AccountService from module core
    private final SendEmailService sendEmailService;
    private final BusService busService;
    private final ClubService clubService;
    private final ClubTypeService clubTypeService;

    @Autowired
    public MemberController(MemberService memberService, SendEmailService sendEmailService, BusService busService, MotorbikeTaxiDriversService motorbikeTaxiDriversService,
                            ClubService clubService, ClubTypeService clubTypeService) {
        this.memberService = memberService;
        this.sendEmailService = sendEmailService;
        this.busService = busService;
        this.clubService = clubService;
        this.clubTypeService = clubTypeService;
    }

    @GetMapping("/members")
    public String members(Model model) {
        backToMembers(model);
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

    //add member
    @PostMapping("/add-member")
    public String addMember(@ModelAttribute("addMember") Member addMember, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        addMember.setMemberRankId(1);
        addMember.setMemberStatusId(1);

        int memberId = memberService.save(addMember);
        if (memberId > 0) {
            backToMembers(model);
            return "members";
        } else {
            return "404";
        }
    }
//
    @GetMapping("/get-one-member")
    public String getOneMember(@RequestParam("id") Integer id, Model model) {
        Member member = memberService.getOne(id);
        backToMembers(model);
        model.addAttribute("oneMember", member);
        return "members";
    }

    @GetMapping("/confirm-delete-member")
    public String deleteMember(@RequestParam("id") Integer id, Model model) {
        memberService.delete(id);
        backToMembers(model);
        return "members";
    }


    @PostMapping("/update-member")
    public String updateMember(@ModelAttribute("updateMember") Member updateMember, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        memberService.update(updateMember);
        backToMembers(model);
        return "members";
    }

    private void backToMembers(Model model) {
        List<Member> members = memberService.getAll();
        List listBus = busService.getAll();
        List listClub = clubService.getAllByType(1);
        List listCLubType = clubTypeService.getAll();

        model.addAttribute("addMember", Member.builder().build());
        model.addAttribute("oneMember", null);
        model.addAttribute("members", members);
        model.addAttribute("listBus", listBus);
        model.addAttribute("listClub", listClub);
        model.addAttribute("listClubType", listCLubType);
    }
}
