package com.hola.holalandcore.service;

import com.hola.holalandcore.entity.User;
import org.springframework.dao.DataAccessException;

public interface UserService {

    User getEmailByRequestRecruitmentId(int id) throws DataAccessException;

}
