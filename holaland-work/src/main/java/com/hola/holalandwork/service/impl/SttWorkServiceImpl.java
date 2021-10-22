package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.SttWork;
import com.hola.holalandwork.repository.SttWorkRepository;
import com.hola.holalandwork.service.SttWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SttWorkServiceImpl implements SttWorkService {

    private final SttWorkRepository sttWorkRepository;

    @Autowired
    public SttWorkServiceImpl(SttWorkRepository sttWorkRepository) {
        this.sttWorkRepository = sttWorkRepository;
    }

    @Override
    public List<SttWork> getAll() throws DataAccessException {
        return sttWorkRepository.getAll();
    }

    @Override
    public SttWork getOne(int id) throws DataAccessException {
        return sttWorkRepository.getOne(id);
    }

}
