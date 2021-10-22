package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.repository.WorkRequestFindJobRepository;
import com.hola.holalandwork.service.WorkRequestFindJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestFindJobServiceImpl implements WorkRequestFindJobService {

    private final WorkRequestFindJobRepository workRequestFindJobRepository;

    @Autowired
    public WorkRequestFindJobServiceImpl(WorkRequestFindJobRepository workRequestFindJobRepository) {
        this.workRequestFindJobRepository = workRequestFindJobRepository;
    }

    @Override
    public List<WorkRequestFindJob> getAll() throws DataAccessException {
        return workRequestFindJobRepository.getAll();
    }

    @Override
    public WorkRequestFindJob getOne(int id) throws DataAccessException {
        return workRequestFindJobRepository.getOne(id);
    }

    @Override
    public List<WorkRequestFindJob> getAllByType(int typeId) throws DataAccessException {
        return workRequestFindJobRepository.getAllByType(typeId);
    }
}
