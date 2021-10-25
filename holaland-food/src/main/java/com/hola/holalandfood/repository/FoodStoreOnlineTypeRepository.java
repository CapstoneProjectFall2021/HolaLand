package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodStoreOnlineType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreOnlineTypeRepository {

    List<FoodStoreOnlineType> getAll() throws DataAccessException;

    FoodStoreOnlineType getOne(int id) throws DataAccessException;
}
