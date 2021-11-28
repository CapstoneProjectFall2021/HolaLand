package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.SttFood;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttFoodService {

    List<SttFood> getAll() throws DataAccessException;

    List<SttFood> getAllByHistoryOrder() throws DataAccessException;

    SttFood getOne(int id) throws DataAccessException;
}
