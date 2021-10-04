package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkTime;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkTimeService {
    List<WorkTime> getAll() throws DataAccessException;
}
