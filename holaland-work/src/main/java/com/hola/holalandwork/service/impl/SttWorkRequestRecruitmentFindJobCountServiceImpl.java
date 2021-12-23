package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import com.hola.holalandwork.repository.SttWorkRequestRecruitmentFindJobCountRepository;
import com.hola.holalandwork.service.SttWorkRequestRecruitmentFindJobCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SttWorkRequestRecruitmentFindJobCountServiceImpl implements SttWorkRequestRecruitmentFindJobCountService {

    private final SttWorkRequestRecruitmentFindJobCountRepository sttWorkRequestRecruitmentFindJobCountRepository;

    @Autowired
    public SttWorkRequestRecruitmentFindJobCountServiceImpl (SttWorkRequestRecruitmentFindJobCountRepository sttWorkRequestRecruitmentFindJobCountRepository) {
        this.sttWorkRequestRecruitmentFindJobCountRepository = sttWorkRequestRecruitmentFindJobCountRepository;
    }

    @Override
    public SttWorkRequestRecruitmentFindJobCount getOneByUserId(int userId) throws DataAccessException {
        try{
            return sttWorkRequestRecruitmentFindJobCountRepository.getOneByUserId(userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
