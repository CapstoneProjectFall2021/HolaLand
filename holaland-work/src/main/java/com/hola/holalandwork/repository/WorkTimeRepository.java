package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkTime;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkTimeRepository {

    List<WorkTime> getAll() throws DataAccessException;

    WorkTime getOne(int id) throws DataAccessException;
}
