package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestRecruitmentService {

    List<WorkRequestRecruitment> getAll() throws DataAccessException;

    WorkRequestRecruitment getOne(int id) throws DataAccessException;

    List<WorkRequestRecruitment> getAllByType(int typeId, int code) throws DataAccessException;
}
