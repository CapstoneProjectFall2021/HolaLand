package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.SttWorkReport;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkReportRepository {

    List<SttWorkReport> getAll() throws DataAccessException;

    SttWorkReport getOne(int id) throws DataAccessException;

}
