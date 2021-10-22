package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWorkRequestApply;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestApplyService {

    List<SttWorkRequestApply> getAll() throws DataAccessException;

    SttWorkRequestApply getOnr(int id) throws DataAccessException;

}
