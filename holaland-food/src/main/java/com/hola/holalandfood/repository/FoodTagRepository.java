package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodTag;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodTagRepository {

    List<FoodTag> getAll() throws DataAccessException;

    FoodTag getOne(int id) throws DataAccessException;
}
