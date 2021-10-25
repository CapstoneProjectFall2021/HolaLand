package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodOrderDetail;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodOrderDetailRepository {
    List<FoodOrderDetail> getAll() throws DataAccessException;

    FoodOrderDetail getOne(int id) throws DataAccessException;
}
