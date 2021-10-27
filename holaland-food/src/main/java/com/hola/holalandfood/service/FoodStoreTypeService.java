package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreTypeService {

    List<FoodStoreType> getAll() throws DataAccessException;

    FoodStoreType getOne(int id) throws DataAccessException;
}
