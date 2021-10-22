package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.SttWorkRequestFindJob;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestFindJobRepository {

    List<SttWorkRequestFindJob> getAll() throws DataAccessException;

    SttWorkRequestFindJob getOne(int id) throws DataAccessException;
}
