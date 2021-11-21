package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkRequestRecruitmentSaved;
import com.hola.holalandwork.repository.WorkRequestRecruitmentSavedRepository;
import com.hola.holalandwork.service.WorkRequestRecruitmentSavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestRecruitmentSavedServiceImpl implements WorkRequestRecruitmentSavedService {

    private final WorkRequestRecruitmentSavedRepository workRequestRecruitmentSavedRepository;

    @Autowired
    public WorkRequestRecruitmentSavedServiceImpl(WorkRequestRecruitmentSavedRepository workRequestRecruitmentSavedRepository) {
        this.workRequestRecruitmentSavedRepository = workRequestRecruitmentSavedRepository;
    }

    @Override
    public List<WorkRequestRecruitmentSaved> getAll() throws DataAccessException {
        return workRequestRecruitmentSavedRepository.getAll();
    }

    @Override
    public List<WorkRequestRecruitment> getAllByAccountId(int accountId) throws DataAccessException {
        return workRequestRecruitmentSavedRepository.getAllByAccountId(accountId);
    }

    @Override
    public WorkRequestRecruitmentSaved getOne(int id) throws DataAccessException {
        return workRequestRecruitmentSavedRepository.getOne(id);
    }

    @Override
    public boolean save(WorkRequestRecruitmentSaved obj) throws DataAccessException {
        return workRequestRecruitmentSavedRepository.save(obj);
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return workRequestRecruitmentSavedRepository.delete(id);
    }

}
