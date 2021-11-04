package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkRequestRecruitmentSaved;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestRecruitmentSavedService {

    List<WorkRequestRecruitmentSaved> getAll() throws DataAccessException;

    List<WorkRequestRecruitment> getAllByAccountId(int accountId) throws DataAccessException;

    WorkRequestRecruitmentSaved getOne(int id) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;

}
