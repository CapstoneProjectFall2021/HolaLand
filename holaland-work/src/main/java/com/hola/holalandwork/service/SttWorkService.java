package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWork;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkService {

    List<SttWork> getAll() throws DataAccessException;

    SttWork getOne(int id) throws DataAccessException;
}
