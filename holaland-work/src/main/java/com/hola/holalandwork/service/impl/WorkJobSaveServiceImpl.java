package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkJobSave;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.repository.WorkJobSaveRepository;
import com.hola.holalandwork.service.WorkJobSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkJobSaveServiceImpl implements WorkJobSaveService {

    private final WorkJobSaveRepository workJobSaveRepository;

    @Autowired
    public WorkJobSaveServiceImpl(WorkJobSaveRepository workJobSaveRepository) {
        this.workJobSaveRepository = workJobSaveRepository;
    }

    @Override
    public List<WorkJobSave> getAll() throws DataAccessException {
        return workJobSaveRepository.getAll();
    }

    @Override
    public List<WorkRequestRecruitment> getAllByAccountId(int accountId) throws DataAccessException {
        return workJobSaveRepository.getAllByAccountId(accountId);
    }

    @Override
    public WorkJobSave getOne(int id) throws DataAccessException {
        return workJobSaveRepository.getOne(id);
    }
}
