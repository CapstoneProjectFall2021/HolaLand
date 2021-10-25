package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreOnlineType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreOnlineTypeService {

    List<FoodStoreOnlineType> getAll() throws DataAccessException;

    FoodStoreOnlineType getOne(int id) throws DataAccessException;
}
