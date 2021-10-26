package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestRecruitmentRepository {

    List<WorkRequestRecruitment> getAll() throws DataAccessException;

    List<WorkRequestRecruitment> getAllByType(int typeId, int sttWorkCode) throws DataAccessException;

    WorkRequestRecruitment getOne(int id) throws DataAccessException;
}
