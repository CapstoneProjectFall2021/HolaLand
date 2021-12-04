package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.mapper.FoodCountSttOrderMapper;
import com.hola.holalandfood.repository.FoodCountSttOrderRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import com.hola.holalandfood.view.FoodCountSttOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FoodCountSttOrderRepositoryImpl implements FoodCountSttOrderRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodCountSttOrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FoodCountSttOrder getCountSttOrderSeller(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(COUNT_STT_ORDER_SELLER, new FoodCountSttOrderMapper(), id, id, id);
    }

    @Override
    public FoodCountSttOrder getCountSttOrderStudent(int user_id) throws DataAccessException {
        return jdbcTemplate.queryForObject(COUNT_STT_ORDER_STUDENT, new FoodCountSttOrderMapper(), user_id, user_id,user_id);
    }
}
