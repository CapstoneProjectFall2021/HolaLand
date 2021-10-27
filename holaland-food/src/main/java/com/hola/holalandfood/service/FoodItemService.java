package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodItem;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodItemService {
    List<FoodItem> getAll() throws DataAccessException;

    FoodItem getOne(int id) throws DataAccessException;
}
