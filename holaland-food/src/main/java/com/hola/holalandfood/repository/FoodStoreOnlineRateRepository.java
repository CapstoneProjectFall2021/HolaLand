package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreOnlineRateRepository {

    List<FoodStoreOnlineRate> getAll() throws DataAccessException;

    List<FoodStoreOnlineRate> getAllCommentByStoreOnlineId(int id) throws DataAccessException;

    FoodStoreOnlineRate getOne(int id) throws DataAccessException;

    FoodStoreOnlineRate getUserComment(int userId, int storeId) throws DataAccessException;

    boolean checkUserCommentExist(int userId, int storeId) throws DataAccessException;

    boolean save(FoodStoreOnlineRate obj) throws DataAccessException;

    boolean update(FoodStoreOnlineRate obj) throws DataAccessException;
}
