package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.SttWorkRequestApply;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestApplyRepository {

    List<SttWorkRequestApply> getAll() throws DataAccessException;

    SttWorkRequestApply getOne(int id) throws DataAccessException;

}
