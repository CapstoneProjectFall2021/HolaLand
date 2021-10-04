package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkSalaryType;
import com.hola.holalandwork.entity.WorkTime;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WorkSalaryTypeService {
    List<WorkSalaryType> getAll() throws DataAccessException;

    WorkSalaryType getOne(int id) throws DataAccessException;
}
