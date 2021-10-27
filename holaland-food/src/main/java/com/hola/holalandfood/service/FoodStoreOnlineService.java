package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodStoreOnline;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreOnlineService {
    List<FoodStoreOnline> getAll() throws DataAccessException;

    FoodStoreOnline getOne(int id) throws DataAccessException;
}
