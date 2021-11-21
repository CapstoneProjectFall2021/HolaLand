package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.repository.WorkRequestApplyRepository;
import com.hola.holalandwork.service.WorkRequestApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestApplyServiceImpl implements WorkRequestApplyService {

    private final WorkRequestApplyRepository workRequestApplyRepository;

    @Autowired
    public WorkRequestApplyServiceImpl(WorkRequestApplyRepository workRequestApplyRepository) {
        this.workRequestApplyRepository = workRequestApplyRepository;
    }

    @Override
    public List<WorkRequestApply> getAll() throws DataAccessException {
        return workRequestApplyRepository.getAll();
    }

    @Override
    public List<WorkRequestRecruitment> getAllAccountId(int accountId) throws DataAccessException {
        return workRequestApplyRepository.getAllByAccountId(accountId);
    }

    @Override
    public WorkRequestApply getOne(int id) throws DataAccessException {
        return workRequestApplyRepository.getOne(id);
    }

    @Override
    public boolean save(WorkRequestApply obj) throws DataAccessException {
        return workRequestApplyRepository.save(obj);
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return workRequestApplyRepository.delete(id);
    }
}
