package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.repository.WorkRequestFindJobRepository;
import com.hola.holalandwork.service.WorkRequestFindJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestFindJobServiceImpl implements WorkRequestFindJobService {

    private final WorkRequestFindJobRepository workRequestFindJobRepository;

    @Autowired
    public WorkRequestFindJobServiceImpl(WorkRequestFindJobRepository workRequestFindJobRepository) {
        this.workRequestFindJobRepository = workRequestFindJobRepository;
    }

    @Override
    public List<WorkRequestFindJob> getAll() throws DataAccessException {
        return workRequestFindJobRepository.getAll();
    }

    @Override
    public WorkRequestFindJob getOne(int id) throws DataAccessException {
        return workRequestFindJobRepository.getOne(id);
    }

    @Override
    public List<WorkRequestFindJob> getAllByType(int typeId, int code) throws DataAccessException {
        return workRequestFindJobRepository.getAllByType(typeId, code);
    }

    @Override
    public List<WorkRequestFindJob> getAllByUserIdAndTypeId(int id, Integer... typeId) throws DataAccessException {
        return workRequestFindJobRepository.getAllByUserIdAndTypeId(id, typeId);
    }

    @Override
    public List<WorkRequestFindJob> getAllListRecruitmentByUserId(int userId, int sttWorkCode) throws DataAccessException {
        return workRequestFindJobRepository.getAllListRecruitmentByUserId(userId, sttWorkCode);
    }

    @Override
    public List<WorkRequestFindJob> searchByTitle(String title, int code) throws DataAccessException {
        return workRequestFindJobRepository.searchByTitle(title, code);
    }

    @Override
    public boolean updateSttRequest(WorkRequestFindJob obj) throws DataAccessException {
        return workRequestFindJobRepository.updateSttRequest(obj);
    }

    @Override
    public boolean save(WorkRequestFindJob obj) throws DataAccessException {
        return workRequestFindJobRepository.save(obj);
    }

    @Override
    public boolean update(WorkRequestFindJob obj) throws DataAccessException {
        return workRequestFindJobRepository.update(obj);
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return workRequestFindJobRepository.delete(id);
    }
}
