package com.hola.holalandwork.service;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestFindJob {
    List<SttWorkRequestFindJob> getAll() throws DataAccessException;
}
