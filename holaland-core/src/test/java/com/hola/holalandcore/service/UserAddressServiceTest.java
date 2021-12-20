package com.hola.holalandcore.service;

import com.hola.holalandcore.entity.UserAddress;
import com.hola.holalandcore.repository.UserAddressRepository;
import com.hola.holalandcore.service.impl.UserAddressServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAddressServiceTest {
    @InjectMocks
    UserAddressServiceImpl userAddressService;

    @Mock
    UserAddressRepository userAddressRepository;

    public UserAddress genUserAddress(){
        UserAddress userAddress = UserAddress.builder()
                .userAddressId(1)
                .userId(1)
                .userName("vietdoan")
                .userPhone("0839696866")
                .userAddress("nam dinh")
                .userAddressDefault(true)
                .build();
        return userAddress;
    }

    @Test
    public void getAllUserAddress() throws Exception {
        List<UserAddress> userAddressList = new ArrayList<>();
        userAddressList.add(genUserAddress());
        when(userAddressRepository.getAllAddressByUserId(1)).thenReturn(userAddressList);
        userAddressService.getAllAddressByUserId(1);
    }

    @Test
    public void getOneByUserIdUserAddress() throws Exception {
        when(userAddressRepository.getOneByUserId(1)).thenReturn(genUserAddress());
        userAddressService.getOneByUserId(1);
    }

    @Test
    public void createUserAddress() throws Exception {
        when(userAddressRepository.save(any())).thenReturn(true);
        userAddressService.save(genUserAddress());
    }

    @Test
    public void updateUserAddress() throws Exception {
        when(userAddressRepository.update(any())).thenReturn(true);
        userAddressService.update(genUserAddress());
    }

    @Test
    public void updateDefaultAddress() throws Exception {
        when(userAddressRepository.updateDefaultAddress(1)).thenReturn(true);
        userAddressService.updateDefaultAddress(1,1);
    }

    @Test
    public void deleteDefaultAddress() throws Exception {
        when(userAddressRepository.delete(1)).thenReturn(true);
        userAddressService.delete(1);
    }

    @Test
    public void deleteDefaultAddressByUserId() throws Exception {
        when(userAddressRepository.deleteDefaultAddressByUserId(1)).thenReturn(true);
        userAddressService.deleteDefaultAddressByUserId(1);
    }
}
