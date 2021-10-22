package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.SttWork;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRepository {

    List<SttWork> getAll() throws DataAccessException;

    SttWork getOne(int id) throws DataAccessException;
}
