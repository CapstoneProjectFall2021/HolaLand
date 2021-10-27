package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodStoreType;
import com.hola.holalandfood.mapper.FoodStoreTypeMapper;
import com.hola.holalandfood.repository.FoodStoreTypeRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodStoreTypeRepositoryImpl implements FoodStoreTypeRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodStoreTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodStoreType> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_STORE_TYPE_GET_ALL, new FoodStoreTypeMapper());
    }

    @Override
    public FoodStoreType getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_STORE_TYPE_GET_ONE, new FoodStoreTypeMapper(), id);
    }
}
