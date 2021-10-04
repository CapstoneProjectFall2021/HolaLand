package com.hola.holalandwork.service;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestFindJobService {
    List<SttWorkRequestFindJobService> getAll() throws DataAccessException;
}
