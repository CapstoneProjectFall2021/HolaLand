package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkTime;
import com.hola.holalandwork.repository.WorkTimeRepository;
import com.hola.holalandwork.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkTimeServiceImpl implements WorkTimeService {

    private  final WorkTimeRepository workTimeRepository;

    @Autowired
    public WorkTimeServiceImpl(WorkTimeRepository workTimeRepository) {
        this.workTimeRepository = workTimeRepository;
    }

    @Override
    public List<WorkTime> getAll() throws DataAccessException {
        return workTimeRepository.getAll();
    }

    @Override
    public WorkTime getOne(int id) throws DataAccessException {
        return workTimeRepository.getOne(id);
    }
}
