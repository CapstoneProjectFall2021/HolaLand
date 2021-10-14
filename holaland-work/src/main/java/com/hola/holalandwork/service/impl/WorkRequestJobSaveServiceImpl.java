package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestJobSave;
import com.hola.holalandwork.repository.WorkRequestJobSaveRepository;
import com.hola.holalandwork.service.WorkRequestJobSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestJobSaveServiceImpl implements WorkRequestJobSaveService {

    private final WorkRequestJobSaveRepository workRequestJobSaveRepository;

    @Autowired
    public WorkRequestJobSaveServiceImpl(WorkRequestJobSaveRepository workRequestJobSaveRepository) {
        this.workRequestJobSaveRepository = workRequestJobSaveRepository;
    }

    @Override
    public List<WorkRequestJobSave> getAll() throws DataAccessException {
        return workRequestJobSaveRepository.getAll();
    }

    @Override
    public WorkRequestJobSave getOne(int id) throws DataAccessException {
        return workRequestJobSaveRepository.getOne(id);
    }
}
