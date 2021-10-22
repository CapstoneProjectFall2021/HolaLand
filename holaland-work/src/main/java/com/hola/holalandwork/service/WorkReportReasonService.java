package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkReportReason;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkReportReasonService {

    List<WorkReportReason> getAll() throws DataAccessException;

    WorkReportReason getOne(int id) throws DataAccessException;

}
