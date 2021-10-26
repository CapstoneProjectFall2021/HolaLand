package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestType;
import com.hola.holalandwork.repository.WorkRequestTypeRepository;
import com.hola.holalandwork.service.WorkRequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestTypeServiceImpl implements WorkRequestTypeService {

    private final WorkRequestTypeRepository workRequestTypeRepository;

    @Autowired
    public WorkRequestTypeServiceImpl(WorkRequestTypeRepository workRequestTypeRepository) {
        this.workRequestTypeRepository = workRequestTypeRepository;
    }

    @Override
    public List<WorkRequestType> getAll() throws DataAccessException {
        return workRequestTypeRepository.getAll();
    }

    @Override
    public WorkRequestType getOne(int id) throws DataAccessException {
        return workRequestTypeRepository.getOne(id);
    }
}
