package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkReport;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkReportRepository {

    List<WorkReport> getAll() throws DataAccessException;

    WorkReport getOne(int id) throws DataAccessException;
}
