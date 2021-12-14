package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestApplyRepository {

    List<WorkRequestApply> getAll() throws DataAccessException;

    List<WorkRequestRecruitment> getAllByAccountId(int accountId) throws DataAccessException;

    WorkRequestApply getOne(int id) throws DataAccessException;

    boolean save(WorkRequestApply obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;

}
