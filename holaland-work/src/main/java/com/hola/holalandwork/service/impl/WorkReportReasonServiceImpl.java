package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkReportReason;
import com.hola.holalandwork.repository.WorkReportReasonRepository;
import com.hola.holalandwork.service.WorkReportReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkReportReasonServiceImpl implements WorkReportReasonService {

    private final WorkReportReasonRepository workReportReasonRepository;

    @Autowired
    public WorkReportReasonServiceImpl(WorkReportReasonRepository workReportReasonRepository) {
        this.workReportReasonRepository = workReportReasonRepository;
    }

    @Override
    public List<WorkReportReason> getAll() throws DataAccessException {
        return workReportReasonRepository.getAll();
    }

    @Override
    public WorkReportReason getOne(int id) throws DataAccessException {
        return workReportReasonRepository.getOne(id);
    }
}
