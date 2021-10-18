package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkSalaryUnit;
import com.hola.holalandwork.repository.WorkSalaryUnitRepository;
import com.hola.holalandwork.service.WorkSalaryUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkSalaryUnitServiceImpl implements WorkSalaryUnitService {

    private final WorkSalaryUnitRepository workSalaryUnitRepository;

    @Autowired
    public WorkSalaryUnitServiceImpl(WorkSalaryUnitRepository workSalaryUnitRepository) {
        this.workSalaryUnitRepository = workSalaryUnitRepository;
    }

    @Override
    public List<WorkSalaryUnit> getAll() throws DataAccessException {
        return workSalaryUnitRepository.getAll();
    }

    @Override
    public WorkSalaryUnit getOne(int id) throws DataAccessException {
        return workSalaryUnitRepository.getOne(id);
    }
}
