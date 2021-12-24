package com.hola.holalandcore.repository;

import com.hola.holalandcore.entity.User;
import org.springframework.dao.DataAccessException;

public interface UserRepository {

    User findByEmail(String email);

    User getEmailByRequestRecruitmentId(int id) throws DataAccessException;

    boolean updatePassword(String newPassword, int userId) throws DataAccessException;
}
