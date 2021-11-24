package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.mapper.FoodItemMapper;
import com.hola.holalandfood.repository.FoodItemRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodItemRepositoryImpl implements FoodItemRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodItem> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_ITEM_GET_ALL, new FoodItemMapper());
    }

    @Override
    public List<FoodItem> getAllByStoreOnlineId(int id) throws DataAccessException {
        return jdbcTemplate.query(FOOD_ITEM_GET_ALL_BY_STORE_ONLINE_ID, new FoodItemMapper(), id);
    }

    @Override
    public List<FoodItem> getAllByStoreOnlineIdAndTagId(int id, int tagId) throws DataAccessException {
        return jdbcTemplate.query(FOOD_ITEM_GET_ALL_BY_STORE_ONLINE_ID_AND_TAG_ID, new FoodItemMapper(), id, tagId);
    }

    @Override
    public FoodItem getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_ITEM_GET_ONE,new FoodItemMapper(),id);
    }

    @Override
    public List<FoodItem> getAllByUserId(int userId) throws DataAccessException {
        return jdbcTemplate.query(FOOD_ITEM_GET_ALL_BY_USER_ID, new FoodItemMapper(), userId);
    }
}
