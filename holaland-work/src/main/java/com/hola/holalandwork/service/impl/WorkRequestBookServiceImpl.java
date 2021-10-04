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

    private  final WorkRequestBookRepository workRequestBookRepository;
    @Autowired
    public WorkRequestBookServiceImpl(WorkRequestBookRepository workRequestBookRepository) {
        this.workRequestBookRepository = workRequestBookRepository;
    }

    @Override
    public List<WorkRequestBook> getAll() throws DataAccessException {
        return workRequestBookRepository.getAll();
    }

    @Override
    public WorkRequestBook getOne(int id) throws DataAccessException {
        return workRequestBookRepository.getOne(id);
    }
}
