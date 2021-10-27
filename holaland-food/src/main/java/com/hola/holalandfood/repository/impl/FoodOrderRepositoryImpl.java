package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.mapper.FoodOrderMapper;
import com.hola.holalandfood.repository.FoodOrderRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodOrderRepositoryImpl implements FoodOrderRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodOrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodOrder> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_ORDER_GET_ALL, new FoodOrderMapper());
    }

    @Override
    public FoodOrder getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_ORDER_GET_ONE, new FoodOrderMapper(),id);
    }
}
