package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestApply;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestApplyService {
    List<WorkRequestApply> getAll() throws DataAccessException;
}
