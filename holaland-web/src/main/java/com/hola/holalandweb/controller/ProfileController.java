package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.Role;
import com.hola.holalandcore.entity.User;
import com.hola.holalandcore.entity.UserAddress;
import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.repository.RoleRepository;
import com.hola.holalandcore.repository.UserRepository;
import com.hola.holalandcore.service.UserAddressService;
import com.hola.holalandcore.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailService userDetailService;
    private final UserAddressService userAddressService;

    private User user;

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

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        this.user = userRepository.findByEmail(principal.getName());
        UserDetail userDetail = userDetailService.getOneByUserId(this.user.getUserId());
        List<UserAddress> userAddressList = userAddressService.getAllAddressByUserDetailId(userDetail.getUserDetailId());
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("userAddressList", userAddressList);
        model.addAttribute("page", 1);
        return "profile";
    }

    @GetMapping("/profile-update")
    public String profileUpdate(Model model) {
        UserDetail userDetail = userDetailService.getOneByUserId(this.user.getUserId());
        UserDetail updateUser = UserDetail.builder().build();
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("updateUser", updateUser);
        model.addAttribute("page", 3);
        return "profile";
    }

    @PostMapping("/update-profile-user")
    public String updateProfileUserByUserId(
            @ModelAttribute("updateUser") UserDetail updateUser,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        updateUser.setUserId(this.user.getUserId());
        boolean isCheck = userDetailService.update(updateUser);
        if(isCheck) {
            return "redirect:" + "/profile-update";
        }else {
            return "404";
        }
    }

    @PostMapping("/update-password-user")
    public String updatePasswordUserByUserId(
            @RequestParam("oldPass") String oldPass,
            @RequestParam("newPass") String newPass,
            @RequestParam("confirmNewPass") String confirmNewPass
    ) {
        boolean isCheck = false;
        if(passwordEncoder.matches(oldPass, this.user.getUserPassword()) && newPass.equals(confirmNewPass)) {
            isCheck = userRepository.updatePassword(passwordEncoder.encode(newPass),this.user.getUserId());
        }
        if(isCheck) {
            return "redirect:" + "/profile-update";
        }else {
            return "404";
        }
    }
}
