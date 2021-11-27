package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodStoreOnlineTag;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodStoreOnlineTagRepository {
    List<FoodStoreOnlineTag> getAll() throws DataAccessException;

    FoodStoreOnlineTag getOne(int id) throws DataAccessException;

    boolean deleteAllTagByFoodStoreOnlineId(int userId) throws DataAccessException;

    int[] insertTagForFoodStore(List<FoodStoreOnlineTag> foodStoreOnlineTags) throws DataAccessException;
}
