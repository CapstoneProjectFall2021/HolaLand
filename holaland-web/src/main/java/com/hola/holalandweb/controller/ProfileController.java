package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.*;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
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

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        UserDetail userDetail = userDetailService.getOneByUserId(currentUser.getId());
        List<UserAddress> userAddressList = userAddressService.getAllAddressByUserDetailId(userDetail.getUserDetailId());
        List<Role> roles = roleRepository.getRolesByUserEmail(currentUser.getUsername());
        String userRole="";
        for (Role role : roles) {
            userRole += (role.getRoleId() == 1 ? "Thành viên" : (role.getRoleId() == 2 ? "Nhà tuyển dụng"
                    : (role.getRoleId() == 3 ? "Bán hàng" : "Khác")))
                    + ((roles.indexOf(role) == (roles.size()-1)) ? "" : ", ");
        }
        model.addAttribute("userRole", userRole);
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("userAddressList", userAddressList);
        model.addAttribute("page", 1);
        return "profile";
    }

    @GetMapping("/profile-update")
    public String profileUpdate(Model model, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        UserDetail userDetail = userDetailService.getOneByUserId(currentUser.getId());
        UserDetail updateUser = UserDetail.builder().build();
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("updateUser", updateUser);
        model.addAttribute("page", 3);
        return "profile";
    }

    @PostMapping("/update-profile-user")
    public String updateProfileUserByUserId(
            @ModelAttribute("updateUser") UserDetail updateUser,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("There was a error " + bindingResult);
            return "404";
        }
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        updateUser.setUserId(currentUser.getId());
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
            @RequestParam("confirmNewPass") String confirmNewPass,
            Authentication authentication
    ) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        boolean isCheck = false;
        if(passwordEncoder.matches(oldPass, currentUser.getPassword()) && newPass.equals(confirmNewPass)) {
            isCheck = userRepository.updatePassword(passwordEncoder.encode(newPass),currentUser.getId());
        }
        if(isCheck) {
            return "redirect:" + "/profile-update";
        }else {
            return "404";
        }
    }

    @GetMapping("/address-update")
    public String addressUpdate(Model model, Authentication authentication) {
        CustomUser currentUser;
        if (authentication != null) {
            currentUser = (CustomUser) authentication.getPrincipal();
        } else {
            return "login";
        }
        List<UserAddress> userAddressList = userAddressService.getAllAddressByUserId(currentUser.getId());
        UserAddress newUserAddress = UserAddress.builder().build();
        model.addAttribute("newUserAddress", newUserAddress);
        model.addAttribute("userAddressList", userAddressList);
        model.addAttribute("page", 4);
        return "profile";
    }

    @PostMapping("/update-address")
    public String updateUserAddress(
            @RequestParam("addressId") int addressId,
            @RequestParam("userName") String userName,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address
    )
    {
        UserAddress newUserAddress = UserAddress.builder().build();
        newUserAddress.setUserAddressId(addressId);
        newUserAddress.setUserName(userName);
        newUserAddress.setUserPhone(phone);
        newUserAddress.setUserAddress(address);
        boolean isCheck = userAddressService.update(newUserAddress);
        if(isCheck) {
            return "redirect:" + "/address-update";
        }else {
            return "404";
        }
    }
}
