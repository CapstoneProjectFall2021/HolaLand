package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodTag;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodTagService {

    List<FoodTag> getAll() throws DataAccessException;

    List<FoodTag> getAllByStoreOnlineId(int id) throws DataAccessException;

    FoodTag getOne(int id) throws DataAccessException;

    List<FoodTag> getAllByUserId(int userId) throws DataAccessException;
}
