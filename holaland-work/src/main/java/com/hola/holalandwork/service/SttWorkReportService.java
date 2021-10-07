package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWorkReport;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkReportService {

    List<SttWorkReport> getAll() throws DataAccessException;
}
