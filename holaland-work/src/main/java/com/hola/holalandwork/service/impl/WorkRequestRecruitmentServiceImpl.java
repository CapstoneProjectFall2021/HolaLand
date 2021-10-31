package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.repository.WorkRequestRecruitmentRepository;
import com.hola.holalandwork.service.WorkRequestRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestRecruitmentServiceImpl implements WorkRequestRecruitmentService {

    private final WorkRequestRecruitmentRepository workRequestRecruitmentRepository;

    @Autowired
    public WorkRequestRecruitmentServiceImpl(WorkRequestRecruitmentRepository workRequestRecruitmentRepository) {
        this.workRequestRecruitmentRepository = workRequestRecruitmentRepository;
    }


    @Override
    public List<WorkRequestRecruitment> getAll() throws DataAccessException {
        return workRequestRecruitmentRepository.getAll();
    }

    @Override
    public WorkRequestRecruitment getOne(int id) throws DataAccessException {
        return workRequestRecruitmentRepository.getOne(id);
    }

    @Override
    public List<WorkRequestRecruitment> getAllByType(int typeId, int sttWorkCode) throws DataAccessException {
        return workRequestRecruitmentRepository.getAllByType(typeId, sttWorkCode);
    }

    @Override
    public boolean save(WorkRequestRecruitment obj) throws DataAccessException {
        return workRequestRecruitmentRepository.save(obj);
    }
}
