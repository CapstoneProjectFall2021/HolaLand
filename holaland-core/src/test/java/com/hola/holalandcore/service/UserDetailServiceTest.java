package com.hola.holalandcore.service;

import com.hola.holalandcore.entity.UserAddress;
import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.repository.UserDetailRepository;
import com.hola.holalandcore.service.impl.UserDetailServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailServiceTest {
    @InjectMocks
    UserDetailServiceImpl userDetailService;

    @Mock
    UserDetailRepository userDetailRepository;

    public UserDetail genUserDetail() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = format.parse("22/10/1999");
        java.sql.Date date = new java.sql.Date(parsed.getTime());
        UserDetail userDetail = UserDetail.builder()
                .userDetailId(1)
                .userId(1)
                .userDetailName("doan viet")
                .userDetailDob(date)
                .userDetailGender(1)
                .userDetailPhone("0839696866")
                .userDetailEmail("vietdoan@gmail.com")
                .build();
        return userDetail;
    }

    @Test
    public void getAllUserBookedByUserId() throws Exception {
        List<UserDetail> userDetailList = new ArrayList<>();
        userDetailList.add(genUserDetail());
        when(userDetailRepository.getAllUserBookedByUserId(1)).thenReturn(userDetailList);
        userDetailService.getAllUserBookedByUserId(1);
    }

    @Test
    public void getAllUserAppliedByUserId() throws Exception {
        List<UserDetail> userDetailList = new ArrayList<>();
        userDetailList.add(genUserDetail());
        when(userDetailRepository.getAllUserAppliedByUserId(1)).thenReturn(userDetailList);
        userDetailService.getAllUserAppliedByUserId(1);
    }

    @Test
    public void getOneByUserId() throws Exception {
        when(userDetailRepository.getOneByUserId(1)).thenReturn(genUserDetail());
        userDetailService.getOneByUserId(1);
    }

    @Test
    public void updateUserDetail() throws Exception {
        when(userDetailRepository.update(any())).thenReturn(true);
        userDetailService.update(genUserDetail());
    }


}
