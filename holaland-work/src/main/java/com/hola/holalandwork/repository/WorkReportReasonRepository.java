package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkReportReason;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkReportReasonRepository {

    List<WorkReportReason> getAll() throws DataAccessException;

    WorkReportReason getOne(int id) throws DataAccessException;

}
