package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.SttWorkRequestFindJob;
import com.hola.holalandwork.repository.SttWorkRequestFindJobRepository;
import com.hola.holalandwork.service.SttWorkRequestFindJobService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SttWorkRequestFindJobServiceImpl implements SttWorkRequestFindJobService {

    private final SttWorkRequestFindJobRepository sttWorkRequestFindJobRepository;

    public SttWorkRequestFindJobServiceImpl(SttWorkRequestFindJobRepository sttWorkRequestFindJobRepository) {
        this.sttWorkRequestFindJobRepository = sttWorkRequestFindJobRepository;
    }

    @Override
    public List<SttWorkRequestFindJob> getAll() throws DataAccessException {
        return sttWorkRequestFindJobRepository.getAll();
    }
}
