package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodStoreType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreTypeRepository {

    List<FoodStoreType> getAll() throws DataAccessException;

    FoodStoreType getOne(int id) throws DataAccessException;
}
