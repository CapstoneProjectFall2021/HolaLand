package com.hola.holalandcore.service;

import com.hola.holalandcore.entity.UserAddress;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserAddressService {

    List<UserAddress> getAllAddressByUserDetailId(int id) throws DataAccessException;

    List<UserAddress> getAllAddressByUserId(int id) throws DataAccessException;

    boolean update(UserAddress obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}
