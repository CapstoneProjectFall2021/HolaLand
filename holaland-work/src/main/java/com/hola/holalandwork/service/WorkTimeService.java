package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkTime;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface WorkTimeService {
    List<WorkTime> getAll() throws DataAccessException;

    WorkTime getOne(int id) throws DataAccessException;
}
