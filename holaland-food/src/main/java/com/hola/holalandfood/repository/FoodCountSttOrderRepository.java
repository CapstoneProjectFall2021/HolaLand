package com.hola.holalandfood.repository;

import com.hola.holalandfood.view.FoodCountSttOrder;
import org.springframework.dao.DataAccessException;

public interface FoodCountSttOrderRepository {

    FoodCountSttOrder getCountSttOrderSeller(int id) throws DataAccessException;

    FoodCountSttOrder getCountSttOrderStudent(int user_id) throws DataAccessException;

}
