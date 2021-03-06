package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodItem;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodItemService {

    List<FoodItem> getAll() throws DataAccessException;

    List<FoodItem> getAllByStoreOnlineId(int id) throws DataAccessException;

    List<FoodItem> getAllByStoreOnlineIdAndTagId(int id, int tagId) throws DataAccessException;

    FoodItem getOne(int id) throws DataAccessException;

    int countItemSold(int id) throws DataAccessException;

    List<FoodItem> getAllByUserId(int userId) throws DataAccessException;

    boolean deletedOne(FoodItem obj) throws DataAccessException;

    boolean save(FoodItem foodItem) throws DataAccessException;

    boolean update(FoodItem obj) throws DataAccessException;

    List<FoodItem> search(String textSearch) throws DataAccessException;
}
