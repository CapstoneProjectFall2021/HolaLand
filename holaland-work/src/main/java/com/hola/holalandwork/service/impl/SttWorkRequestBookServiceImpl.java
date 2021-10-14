package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.SttWorkRequestBook;
import com.hola.holalandwork.repository.SttWorkRequestBookRepository;
import com.hola.holalandwork.service.SttWorkRequestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SttWorkRequestBookServiceImpl implements SttWorkRequestBookService {

    private final SttWorkRequestBookRepository sttWorkRequestBookRepository;

    @Autowired
    public SttWorkRequestBookServiceImpl(SttWorkRequestBookRepository sttWorkRequestBookRepository) {
        this.sttWorkRequestBookRepository = sttWorkRequestBookRepository;
    }

    @Override
    public List<SttWorkRequestBook> getAll() throws DataAccessException {
        return sttWorkRequestBookRepository.getAll();
    }

    @Override
    public SttWorkRequestBook getOne(int id) throws DataAccessException {
        return sttWorkRequestBookRepository.getOne(id);
    }
}
