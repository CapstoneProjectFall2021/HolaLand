package com.hola.holalandcore.repository;

import com.hola.holalandcore.entity.UserAddress;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserAddressRepository {

    List<UserAddress> getAllAddressByUserId(int id) throws DataAccessException;

    UserAddress getOneByUserId(int id) throws DataAccessException;

    boolean save(UserAddress obj) throws DataAccessException;

    boolean update(UserAddress obj) throws DataAccessException;

    boolean updateDefaultAddress(int id) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;

    boolean deleteDefaultAddressByUserId(int userId) throws DataAccessException;
}
