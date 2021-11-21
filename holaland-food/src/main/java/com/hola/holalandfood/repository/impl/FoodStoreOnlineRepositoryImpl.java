package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.mapper.FoodStoreOnlineMapper;
import com.hola.holalandfood.repository.FoodStoreOnlineRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodStoreOnlineRepositoryImpl implements FoodStoreOnlineRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodStoreOnlineRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodStoreOnline> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_STORE_ONLINE_GET_ALL,new FoodStoreOnlineMapper());
    }

    @Override
    public List<FoodStoreOnline> getAllByType(int typeId, int code) throws DataAccessException {
        return jdbcTemplate.query(FOOD_STORE_ONLINE_GET_ALL_BY_TYPE, new FoodStoreOnlineMapper(), typeId, code);
    }

    @Override
    public FoodStoreOnline getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_STORE_ONLINE_GET_ONE,new FoodStoreOnlineMapper(),id);
    }
}
