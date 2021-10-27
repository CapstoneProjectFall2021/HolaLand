package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodItem;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodItemRepository {
    List<FoodItem> getAll() throws DataAccessException;

    FoodItem getOne(int id) throws DataAccessException;
}
