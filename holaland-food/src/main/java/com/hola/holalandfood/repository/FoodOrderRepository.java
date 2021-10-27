package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodOrder;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodOrderRepository {
    List<FoodOrder> getAll() throws DataAccessException;

    FoodOrder getOne(int id) throws DataAccessException;
}
