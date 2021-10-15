package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestApplyService {

    List<WorkRequestApply> getAll() throws DataAccessException;

    List<WorkRequestRecruitment> getAllAccountId(int accountId) throws DataAccessException;

    WorkRequestApply getOne(int id) throws DataAccessException;
}
