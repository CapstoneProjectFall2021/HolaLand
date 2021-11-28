package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodOrder;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodOrderService {

    List<FoodOrder> getAll() throws DataAccessException;

    List<FoodOrder> getAllByUserIdAndStatus(int userId, Integer... status) throws DataAccessException;

    FoodOrder getOne(int id) throws DataAccessException;

    Boolean updateSttFood(FoodOrder  obj) throws DataAccessException;
}
