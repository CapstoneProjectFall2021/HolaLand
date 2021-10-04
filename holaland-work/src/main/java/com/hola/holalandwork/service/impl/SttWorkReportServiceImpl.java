package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.SttWorkReport;
import com.hola.holalandwork.repository.SttWorkReportRepository;
import com.hola.holalandwork.service.SttWorkReportService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SttWorkReportServiceImpl implements SttWorkReportService {

    private final SttWorkReportRepository sttWorkReportRepository;

    public SttWorkReportServiceImpl(SttWorkReportRepository sttWorkReportRepository) {
        this.sttWorkReportRepository = sttWorkReportRepository;
    }

    @Override
    public List<SttWorkReport> getAll() throws DataAccessException {
        return sttWorkReportRepository.getAll();
    }
}
