package com.hola.holalandweb.controller;

import com.hola.holalandcore.repository.UserRepository;
import com.hola.holalandweb.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class MainController {

    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
    public String admin(Model model, Principal principal, Authentication authentication) {
        String email = principal.getName();
        com.hola.holalandcore.entity.User user = userRepository.findByEmail(email);
        model.addAttribute("userInfo", user);
        model.addAttribute("userRole", authentication.getAuthorities());
        return "show-info";
    }

    @GetMapping("/403")
    public String accessDenied(Principal principal) {
        if (principal != null) {
            User currentUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(currentUser);
            String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
            System.err.println(userInfo);
            System.err.println(message);
        }
        return "403";
    }
}
