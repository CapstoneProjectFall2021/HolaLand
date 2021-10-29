package com.hola.holalandweb.controller;

import com.hola.holalandweb.util.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login";
    }

    @GetMapping("/show-info")
    public String admin() {
        return "show-info";
    }

//    @GetMapping("/works")
//    public String loginOK(Model model, Principal principal) {
//
//        // After login success will have principal
//        String userName = principal.getName();
//        System.out.println("USER NAME: " + userName);
//
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
//
//        return "index";
//    }

    @GetMapping("/403")
    public String accessDenied(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
            System.err.println(userInfo);
            System.err.println(message);
        }
        return "403";
    }
}