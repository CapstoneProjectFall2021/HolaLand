package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.entity.Role;
import com.hola.holalandcore.entity.User;
import com.hola.holalandcore.entity.UserAddress;
import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.repository.RoleRepository;
import com.hola.holalandcore.repository.UserRepository;
import com.hola.holalandcore.service.UserAddressService;
import com.hola.holalandcore.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailService userDetailService;
    private final UserAddressService userAddressService;

    @Autowired
    public ProfileController(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserDetailService userDetailService,
            UserAddressService userAddressService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDetailService = userDetailService;
        this.userAddressService = userAddressService;
    }

    @GetMapping("")
    public String profile(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        UserDetail userDetail = userDetailService.getOneByUserId(currentUser.getId());
        List<UserAddress> userAddressList = userAddressService.getAllAddressByUserDetailId(userDetail.getUserDetailId());

        model.addAttribute("userDetail", userDetail);
        model.addAttribute("userAddressList", userAddressList);
        model.addAttribute("page", 1);
        return "profile";
    }

    @GetMapping("/update")
    public String profileUpdate(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        UserDetail userDetail = userDetailService.getOneByUserId(currentUser.getId());

        UserDetail updateUser = UserDetail.builder().build();
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("updateUser", updateUser);
        model.addAttribute("page", 3);
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfileUserByUserId(
            @ModelAttribute("updateUser") UserDetail updateUser,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        updateUser.setUserId(currentUser.getId());
        boolean isCheck = userDetailService.update(updateUser);
        if (isCheck) {
            return "redirect:" + "/profile/update";
        } else {
            return "404";
        }
    }

    @PostMapping("/change-password")
    public String updatePasswordUserByUserId(
            @RequestParam("oldPass") String oldPass,
            @RequestParam("newPass") String newPass,
            @RequestParam("confirmNewPass") String confirmNewPass,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        boolean isCheck = false;
        if (passwordEncoder.matches(oldPass, currentUser.getPassword()) && newPass.equals(confirmNewPass)) {
            isCheck = userRepository.updatePassword(passwordEncoder.encode(newPass), currentUser.getId());
        }
        if (isCheck) {
            return "redirect:" + "/profile/update";
        } else {
            return "404";
        }
    }
}
