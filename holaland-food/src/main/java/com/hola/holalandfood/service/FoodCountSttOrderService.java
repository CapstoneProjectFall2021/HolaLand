package com.hola.holalandfood.service;

import com.hola.holalandfood.view.FoodCountSttOrder;
import org.springframework.dao.DataAccessException;

public interface FoodCountSttOrderService {

    FoodCountSttOrder getCountSttOrderSeller(int id) throws DataAccessException;

    FoodCountSttOrder getCountSttOrderStudent(int user_id) throws DataAccessException;

}
