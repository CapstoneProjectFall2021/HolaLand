package com.hola.holalandcore.service.impl;

import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.repository.UserDetailRepository;
import com.hola.holalandcore.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public List<UserDetail> getAllUserBookedByUserId(int id) throws DataAccessException {
        return userDetailRepository.getAllUserBookedByUserId(id);
    }

    @Override
    public List<UserDetail> getAllUserAppliedByUserId(int id) throws DataAccessException {
        return userDetailRepository.getAllUserAppliedByUserId(id);
    }

    @Override
    public UserDetail getOneByUserId(int id) throws DataAccessException {
        return userDetailRepository.getOneByUserId(id);
    }

    @Override
    public boolean update(UserDetail obj) throws DataAccessException {
        return userDetailRepository.update(obj);
    }
}
