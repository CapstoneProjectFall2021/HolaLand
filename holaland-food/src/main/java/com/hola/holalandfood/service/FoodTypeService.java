package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodTypeService {

    List<FoodType> getAll() throws DataAccessException;

    FoodType getOne(int id) throws DataAccessException;
}
