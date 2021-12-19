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
    public List<WorkRequestRecruitment> getAllByUserIdAndTypeId(int id, Integer... typeId) throws DataAccessException {
        return workRequestRecruitmentRepository.getAllByUserIdAndTypeId(id, typeId);
    }

    @Override
    public List<WorkRequestRecruitment> getAllListAppliedByUserId(int userId, int sttWorkCode) throws DataAccessException {
        return workRequestRecruitmentRepository.getAllListAppliedByUserId(userId, sttWorkCode);
    }

    @Override
    public boolean save(WorkRequestRecruitment obj) throws DataAccessException {
        return workRequestRecruitmentRepository.save(obj);
    }

    @Override
    public boolean updateSttRequest(WorkRequestRecruitment obj) throws DataAccessException {
        return workRequestRecruitmentRepository.updateSttRequest(obj);
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return workRequestRecruitmentRepository.delete(id);
    }
}
