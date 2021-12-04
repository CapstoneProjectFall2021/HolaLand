package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.SttFood;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SttFoodRepository {

    List<SttFood> getAll() throws DataAccessException;

    List<SttFood> getAllHistoryOrder() throws DataAccessException;

    SttFood getOne(int id) throws DataAccessException;
}
