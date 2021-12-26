package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodType;
import com.hola.holalandfood.mapper.FoodTypeMapper;
import com.hola.holalandfood.repository.FoodTypeRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodTypeRepositoryImpl implements FoodTypeRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodType> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_TYPE_GET_ALL, new FoodTypeMapper());
    }

    @Override
    public List<FoodType> getAllByUserId(int userId) throws DataAccessException {
        return jdbcTemplate.query(FOOD_TYPE_GET_ALL_BY_USER_ID, new FoodTypeMapper(), userId);
    }

    @Override
    public FoodType getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_TYPE_GET_ONE, new FoodTypeMapper(), id);
    }
}
