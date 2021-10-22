package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.SttWorkRequestBook;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestBookRepository {

    List<SttWorkRequestBook> getAll() throws DataAccessException;

    SttWorkRequestBook getOne(int id) throws DataAccessException;

}
