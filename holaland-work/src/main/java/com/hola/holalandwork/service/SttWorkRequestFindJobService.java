package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWorkRequestFindJob;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestFindJobService {

    List<SttWorkRequestFindJob> getAll() throws DataAccessException;
}
