package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkReport;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkReportService {

    List<WorkReport> getAll() throws DataAccessException;
}
