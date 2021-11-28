package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodItem;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodItemRepository {

    List<FoodItem> getAll() throws DataAccessException;

    List<FoodItem> getAllByStoreOnlineId(int id) throws DataAccessException;

    List<FoodItem> getAllByStoreOnlineIdAndTagId(int id, int tagId) throws DataAccessException;

    FoodItem getOne(int id) throws DataAccessException;

    List<FoodItem> getAllByUserId(int userId) throws DataAccessException;

    boolean deletedOne(FoodItem obj) throws DataAccessException;
}
