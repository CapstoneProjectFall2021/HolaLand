package com.hola.holalandcore.repository;

import com.hola.holalandcore.entity.UserDetail;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDetailRepository {

    List<UserDetail> getAllUserBookedByUserId(int id) throws DataAccessException;

    List<UserDetail> getAllUserAppliedByUserId(int id) throws DataAccessException;

    UserDetail getOneByUserId(int id) throws DataAccessException;

    boolean update(UserDetail obj) throws DataAccessException;
}
