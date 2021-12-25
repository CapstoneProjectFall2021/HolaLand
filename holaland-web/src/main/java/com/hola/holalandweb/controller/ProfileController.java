package com.hola.holalandweb.controller;

import com.hola.holalandcore.entity.CustomUser;
import com.hola.holalandcore.entity.Role;
import com.hola.holalandcore.entity.UserAddress;
import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.repository.RoleRepository;
import com.hola.holalandcore.repository.UserRepository;
import com.hola.holalandcore.service.RoleService;
import com.hola.holalandcore.service.UserAddressService;
import com.hola.holalandcore.service.UserDetailService;
import com.hola.holalandcore.service.UserService;
import com.hola.holalandcore.util.Format;
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

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final UserDetailService userDetailService;
    private final UserAddressService userAddressService;

    @Autowired
    public ProfileController(
            PasswordEncoder passwordEncoder,
            UserService userService,
            RoleService roleService,
            UserDetailService userDetailService,
            UserAddressService userAddressService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailService = userDetailService;
        this.userAddressService = userAddressService;
    }

    @GetMapping("")
    public String profile(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        UserDetail userDetail = userDetailService.getOneByUserId(currentUser.getId());
        List<UserAddress> userAddressList = userAddressService.getAllAddressByUserId(userDetail.getUserDetailId());
        List<Role> roles = roleService.getRolesByUserEmail(currentUser.getUsername());
        String userRole="";
        for (Role role : roles) {
            userRole += (role.getRoleId() == 1 ? "Thành viên" : (role.getRoleId() == 2 ? "Nhà tuyển dụng"
                    : (role.getRoleId() == 3 ? "Bán hàng" : "Học sinh")))
                    + ((roles.indexOf(role) == (roles.size()-1)) ? "" : ", ");
        }
        model.addAttribute("format", new Format());
        model.addAttribute("userRole", userRole);
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

    @PostMapping("/password/update")
    public String updatePasswordUserByUserId(
            @RequestParam("oldPass") String oldPass,
            @RequestParam("newPass") String newPass,
            @RequestParam("confirmNewPass") String confirmNewPass,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        boolean isCheck = false;
        if (passwordEncoder.matches(oldPass, currentUser.getCustomUserPassword()) && newPass.equals(confirmNewPass)) {
            isCheck = userService.updatePassword(passwordEncoder.encode(newPass), currentUser.getId());
        }
        if (isCheck) {
            return "redirect:" + "/profile/update";
        } else {
            return "404";
        }
    }

    @GetMapping("/address/update")
    public String addressUpdate(Model model, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        List<UserAddress> userAddressList = userAddressService.getAllAddressByUserId(currentUser.getId());
        model.addAttribute("format", new Format());
        model.addAttribute("userAddressList", userAddressList);
        model.addAttribute("page", 4);
        return "profile";
    }

    @PostMapping("/address/update")
    public String updateUserAddress(
            @RequestParam("addressId") int addressId,
            @RequestParam("userName") String userName,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address
    ) {
        UserAddress newUserAddress = UserAddress.builder()
                .userAddressId(addressId)
                .userName(userName)
                .userPhone(phone)
                .userAddress(address)
                .build();
        boolean isCheck = userAddressService.update(newUserAddress);
        if(isCheck) {
            return "redirect:" + "/profile/address/update";
        }else {
            return "404";
        }
    }

    @PostMapping("/address/add")
    public String addNewUserAddress(
            @RequestParam("newUserName") String newUserName,
            @RequestParam("newPhone") String newPhone,
            @RequestParam("newAddress") String newAddress,
            Authentication authentication
    ) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();

        UserAddress newUserAddress = UserAddress.builder()
                .userId(currentUser.getId())
                .userName(newUserName)
                .userPhone(newPhone)
                .userAddress(newAddress)
                .userAddressDeleted(false)
                .build();
        boolean isCheck = userAddressService.save(newUserAddress);
        if(isCheck) {
            return "redirect:" + "/profile/address/update";
        }else {
            return "404";
        }
    }

    @GetMapping("/address/delete")
    public String deleteUserAddress(@RequestParam("addressId") int addressId) {
        boolean isCheck = userAddressService.delete(addressId);
        if(isCheck) {
            return "redirect:" + "/profile/address/update";
        }else {
            return "404";
        }
    }

    @GetMapping("/address/default")
    public String changeUserAddressDefault(@RequestParam("addressId") int addressId, Authentication authentication) {
        CustomUser currentUser = (CustomUser) authentication.getPrincipal();
        boolean isCheck = userAddressService.updateDefaultAddress(addressId, currentUser.getId());
        if(isCheck) {
            return "redirect:" + "/profile/address/update";
        }else {
            return "404";
        }
    }
}
