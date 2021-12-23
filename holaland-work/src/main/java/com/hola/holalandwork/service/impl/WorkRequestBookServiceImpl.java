package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkRequestBook;
import com.hola.holalandwork.repository.WorkRequestBookRepository;
import com.hola.holalandwork.service.WorkRequestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestBookServiceImpl implements WorkRequestBookService {

    private final WorkRequestBookRepository workRequestBookRepository;

    @Autowired
    public WorkRequestBookServiceImpl(WorkRequestBookRepository workRequestBookRepository) {
        this.workRequestBookRepository = workRequestBookRepository;
    }

    @Override
    public List<WorkRequestBook> getAll() throws DataAccessException {
        return workRequestBookRepository.getAll();
    }

    @Override
    public List<WorkRequestBook> getAllByRequestId(int id) throws DataAccessException {
        return workRequestBookRepository.getAllByRequestId(id);
    }

    @Override
    public WorkRequestBook getOne(int id) throws DataAccessException {
        return workRequestBookRepository.getOne(id);
    }

    @Override
    public boolean checkUserIsBooked(int userId, int findJobId) throws DataAccessException {
        return workRequestBookRepository.checkUserIsBooked(userId, findJobId);
    }

    @Override
    public boolean userAcceptRecruiterBooked(WorkRequestBook obj) throws DataAccessException {
        boolean isCheck = workRequestBookRepository.rejectAllRequestByFindJobId(obj);
        if(isCheck) {
            return workRequestBookRepository.updateStatusRequestByUserIdAndFindJobId(obj);
        } else {
            return false;
        }
    }

    @Override
    public boolean userRejectRecruiterBooked(WorkRequestBook obj) throws DataAccessException {
        return workRequestBookRepository.updateStatusRequestByUserIdAndFindJobId(obj);
    }

    @Override
    public boolean save(WorkRequestBook obj) throws DataAccessException {
        return workRequestBookRepository.save(obj);
    }
}
