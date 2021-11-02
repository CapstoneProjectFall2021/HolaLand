package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import org.springframework.dao.DataAccessException;

public interface SttWorkRequestRecruitmentFindJobCountRepository {

    SttWorkRequestRecruitmentFindJobCount getOneByUserId(int userId) throws DataAccessException;
}
