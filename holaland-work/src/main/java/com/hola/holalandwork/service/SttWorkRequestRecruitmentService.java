package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestRecruitmentService {

    List<SttWorkRequestRecruitment> getAll() throws DataAccessException;
}
