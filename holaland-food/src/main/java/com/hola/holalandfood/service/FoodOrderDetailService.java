package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodOrderDetail;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodOrderDetailService {
    List<FoodOrderDetail> getAll() throws DataAccessException;

    List<FoodOrderDetail> getAllByOrderId(int orderId) throws DataAccessException;

    FoodOrderDetail getOne(int id) throws DataAccessException;
}
