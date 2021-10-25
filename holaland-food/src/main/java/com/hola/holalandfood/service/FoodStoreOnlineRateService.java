package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreOnlineRateService {
    List<FoodStoreOnlineRate> getAll() throws DataAccessException;

    FoodStoreOnlineRate getOne(int id) throws DataAccessException;
}
