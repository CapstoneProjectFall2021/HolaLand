package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodTypeRepository {

    List<FoodType> getAll() throws DataAccessException;

    List<FoodType> getAllByUserId(int userId) throws DataAccessException;

    FoodType getOne(int id) throws DataAccessException;
}
