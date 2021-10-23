package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkReport;
import com.hola.holalandwork.repository.WorkReportRepository;
import com.hola.holalandwork.service.WorkReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkReportServiceImpl implements WorkReportService {

    private final WorkReportRepository workReportRepository;

    @Autowired
    public WorkReportServiceImpl(WorkReportRepository workReportRepository) {
        this.workReportRepository = workReportRepository;
    }

    @Override
    public List<WorkReport> getAll() throws DataAccessException {
        return workReportRepository.getAll();
    }
}
