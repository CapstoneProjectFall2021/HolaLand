package com.hola.holalandweb.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
