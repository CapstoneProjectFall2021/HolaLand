package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkJobType;
import com.hola.holalandwork.repository.WorkJobTypeRepository;
import com.hola.holalandwork.service.WorkJobTypeService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkJobTypeServiceImpl implements WorkJobTypeService {

    private final WorkJobTypeRepository workJobTypeRepository;

    public WorkJobTypeServiceImpl(WorkJobTypeRepository workJobTypeRepository) {
        this.workJobTypeRepository = workJobTypeRepository;
    }

    @Override
    public List<WorkJobType> getAll() throws DataAccessException {
        return workJobTypeRepository.getAll();
    }
}
