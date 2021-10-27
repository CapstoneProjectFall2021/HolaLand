package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodStoreOnlineType;
import com.hola.holalandfood.mapper.FoodStoreOnlineTypeMapper;
import com.hola.holalandfood.repository.FoodStoreOnlineTypeRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodStoreOnlineTypeRepositoryImpl implements FoodStoreOnlineTypeRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodStoreOnlineTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodStoreOnlineType> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_STORE_ONLINE_TYPE_GET_ALL, new FoodStoreOnlineTypeMapper());
    }

    @Override
    public FoodStoreOnlineType getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_STORE_ONLINE_TYPE_GET_ONE, new FoodStoreOnlineTypeMapper(), id);
    }
}
