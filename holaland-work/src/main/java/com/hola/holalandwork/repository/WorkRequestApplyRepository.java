package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestApply;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestApplyRepository {

    List<WorkRequestApply> getAll() throws DataAccessException;

    WorkRequestApply getOne(int id) throws DataAccessException;

}
