package com.hola.holalandcore.service;

import com.hola.holalandcore.entity.UserDetail;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDetailService {

    List<UserDetail> getAll() throws DataAccessException;

    List<UserDetail> getAllUserBookedByUserId(int id) throws DataAccessException;

    List<UserDetail> getAllUserAppliedByUserId(int id) throws DataAccessException;
}
