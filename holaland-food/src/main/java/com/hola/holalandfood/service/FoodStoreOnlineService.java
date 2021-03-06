package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreOnline;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreOnlineService {

    List<FoodStoreOnline> getAll() throws DataAccessException;

    List<FoodStoreOnline> getAllByType(int typeId, int code) throws DataAccessException;

    FoodStoreOnline getOne(int id) throws DataAccessException;

    FoodStoreOnline getOneByUserId(int userId) throws DataAccessException;

    FoodStoreOnline getOneByOrderId(int orderId) throws DataAccessException;

    boolean checkUserIsOwner(int userId, int storeId) throws DataAccessException;

    boolean updateShopInfo(FoodStoreOnline obj) throws DataAccessException;

    boolean isShopStopSelling(FoodStoreOnline obj) throws DataAccessException;

    boolean isShopPauseSelling(FoodStoreOnline obj) throws DataAccessException;
}
