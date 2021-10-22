package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.SttWorkRequestRecruitment;
import com.hola.holalandwork.repository.SttWorkRequestRecruitmentRepository;
import com.hola.holalandwork.service.SttWorkRequestRecruitmentService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SttWorkRequestRecruitmentServiceImpl implements SttWorkRequestRecruitmentService {

    private final SttWorkRequestRecruitmentRepository sttWorkRequestRecruitmentRepository;

    public SttWorkRequestRecruitmentServiceImpl(SttWorkRequestRecruitmentRepository sttWorkRequestRecruitmentRepository) {
        this.sttWorkRequestRecruitmentRepository = sttWorkRequestRecruitmentRepository;
    }

    @Override
    public List<SttWorkRequestRecruitment> getAll() throws DataAccessException {
        return sttWorkRequestRecruitmentRepository.getAll();
    }
}
