package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodOrder;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodOrderRepository {

    List<FoodOrder> getAll() throws DataAccessException;

    List<FoodOrder> getAllByStoreOnlineId(int id) throws DataAccessException;

    List<FoodOrder> getAllUserOrderByUserIdAndStatus(int userId, Integer... status) throws DataAccessException;

    List<FoodOrder> getAllSellerOrderByUserIdAndStatus(int userId, Integer... status) throws DataAccessException;

    FoodOrder getOne(int id) throws DataAccessException;

    int save(FoodOrder obj) throws DataAccessException;

    boolean checkUserOrder(int storeId, int userId) throws DataAccessException;

    boolean updateSttFood(FoodOrder obj) throws DataAccessException;

    boolean addReasonReject(FoodOrder obj) throws DataAccessException;

    // Statistic
    List<FoodOrder> getAllFoodOrderByDay(long startOfDay, long endOfDay, int storeId);
}
