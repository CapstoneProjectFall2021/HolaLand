package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkSalaryType;
import com.hola.holalandwork.repository.WorkSalaryTypeRepository;
import com.hola.holalandwork.service.WorkSalaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkSalaryTypeServiceImpl implements WorkSalaryTypeService {

    private final WorkSalaryTypeRepository workSalaryTypeRepository;

    @Autowired
    public WorkSalaryTypeServiceImpl(WorkSalaryTypeRepository workSalaryTypeRepository) {
        this.workSalaryTypeRepository = workSalaryTypeRepository;
    }

    @Override
    public List<WorkSalaryType> getAll() throws DataAccessException {
        return workSalaryTypeRepository.getAll();
    }

    @Override
    public WorkSalaryType getOne(int id) throws DataAccessException {
        return workSalaryTypeRepository.getOne(id);
    }
}
