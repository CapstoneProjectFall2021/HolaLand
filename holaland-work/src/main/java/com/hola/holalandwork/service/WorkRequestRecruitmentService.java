package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestRecruitmentService {
    List<WorkRequestRecruitment> getAll() throws DataAccessException;
}
