package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.repository.WorkRequestApplyRepository;
import com.hola.holalandwork.service.WorkRequestApplyService;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class WorkRequestApplyServiceImpl implements WorkRequestApplyService {

    private final WorkRequestApplyRepository workRequestApplyRepository;

    public WorkRequestApplyServiceImpl(WorkRequestApplyRepository workRequestApplyRepository) {
        this.workRequestApplyRepository = workRequestApplyRepository;
    }

    @Override
    public List<WorkRequestApply> getAll() throws DataAccessException {
        return workRequestApplyRepository.getAll();
    }

    @Override
    public WorkRequestApply getOne(int id) throws DataAccessException {
        return workRequestApplyRepository.getOne(id);
    }
}
