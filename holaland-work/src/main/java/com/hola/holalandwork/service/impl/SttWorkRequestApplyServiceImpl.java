package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.SttWorkRequestApply;
import com.hola.holalandwork.repository.SttWorkRequestApplyRepository;
import com.hola.holalandwork.service.SttWorkRequestApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SttWorkRequestApplyServiceImpl implements SttWorkRequestApplyService {

    private final SttWorkRequestApplyRepository sttWorkRequestApplyRepository;

    @Autowired
    public SttWorkRequestApplyServiceImpl(SttWorkRequestApplyRepository sttWorkRequestApplyRepository) {
        this.sttWorkRequestApplyRepository = sttWorkRequestApplyRepository;
    }

    @Override
    public List<SttWorkRequestApply> getAll() throws DataAccessException {
        return sttWorkRequestApplyRepository.getAll();
    }

    @Override
    public SttWorkRequestApply getOnr(int id) throws DataAccessException {
        return sttWorkRequestApplyRepository.getOne(id);
    }
}
