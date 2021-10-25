package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.mapper.FoodOrderDetailMapper;
import com.hola.holalandfood.repository.FoodOrderDetailRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodOrderDetailRepositoryImpl implements FoodOrderDetailRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodOrderDetailRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodOrderDetail> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_ORDER_DETAIL_GET_ALL,new FoodOrderDetailMapper());
    }

    @Override
    public FoodOrderDetail getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_ORDER_DETAIL_GET_ONE,new FoodOrderDetailMapper(),id);
    }
}
