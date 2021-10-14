package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.SttWorkRequestBook;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttWorkRequestBookService {

    List<SttWorkRequestBook> getAll() throws DataAccessException;

    SttWorkRequestBook getOne(int id) throws DataAccessException;

}
