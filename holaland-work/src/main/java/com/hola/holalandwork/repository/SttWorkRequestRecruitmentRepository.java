package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.SttWorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestRecruitmentRepository {

    List<SttWorkRequestRecruitment> getAll() throws DataAccessException;

    SttWorkRequestRecruitment getOne(int id) throws DataAccessException;
}
