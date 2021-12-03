package com.hola.holalandcore.repository;

import com.hola.holalandcore.entity.UserAddress;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserAddressRepository {

    List<UserAddress> getAllAddressByUserDetailId(int id) throws DataAccessException;

    List<UserAddress> getAllAddressByUserId(int id) throws DataAccessException;
}
