package com.hola.holalandcore.service;

import com.hola.holalandcore.entity.UserAddress;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserAddressService {

    List<UserAddress> getAllAddressByUserId(int id) throws DataAccessException;

    UserAddress getOneByUserId(int id) throws DataAccessException;

    boolean save(UserAddress obj) throws DataAccessException;

    boolean update(UserAddress obj) throws DataAccessException;

    boolean updateDefaultAddress(boolean isDefault, int id) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}
