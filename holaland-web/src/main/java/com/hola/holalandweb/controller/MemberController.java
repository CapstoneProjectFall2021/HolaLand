package com.hola.holalandweb.controller;

import com.hola.holalandcore.service.AccountService;
import com.hola.holalandfptu.service.ClubService;
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

    private void backToMembers(Model model) {
        List<Member> members = memberService.getAll();
        model.addAttribute("addMember", Member.builder().build());
        model.addAttribute("members", members);
    }
}
