package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestApplyService {

    List<WorkRequestApply> getAll() throws DataAccessException;

    List<WorkRequestRecruitment> getAllAccountId(int accountId) throws DataAccessException;

    List<WorkRequestApply> getAllByRequestId(int id) throws DataAccessException;

    WorkRequestApply getOne(int id) throws DataAccessException;

    boolean save(WorkRequestApply obj) throws DataAccessException;

    boolean recruiterAcceptUserApply(WorkRequestApply obj) throws DataAccessException;

    boolean recruiterRejectUserApply(WorkRequestApply obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}
