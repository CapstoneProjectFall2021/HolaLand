package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import org.springframework.dao.DataAccessException;

public interface SttWorkRequestRecruitmentFindJobCountService {

    SttWorkRequestRecruitmentFindJobCount getOneByUserId(int userId) throws DataAccessException;
}
