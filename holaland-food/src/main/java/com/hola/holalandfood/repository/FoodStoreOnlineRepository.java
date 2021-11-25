package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodStoreOnline;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreOnlineRepository {
    List<FoodStoreOnline> getAll() throws DataAccessException;

    List<FoodStoreOnline> getAllByType(int typeId, int code) throws DataAccessException;

    FoodStoreOnline getOne(int id) throws DataAccessException;

    FoodStoreOnline getOneByUserId(int userId) throws DataAccessException;

    boolean updateShopInfo(FoodStoreOnline obj) throws DataAccessException;
}
