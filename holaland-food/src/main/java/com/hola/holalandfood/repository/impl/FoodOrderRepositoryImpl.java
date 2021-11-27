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
    public List<FoodOrder> getAllByUserIdAndStatus(int userId, Integer... status) throws DataAccessException {
        StringBuilder s = new StringBuilder("(");
        for (int i = 0; i < status.length; i++) {
            s.append((i != status.length - 1) ? status[i] + "," : status[i] + ")");
        }
        String sql = "SELECT * FROM food_order WHERE user_id = ? AND stt_food_code in "
                + s
                + " AND food_order_deleted = 0 "
                + "ORDER BY food_order_created_date DESC ";
        return jdbcTemplate.query(sql, new FoodOrderMapper(), userId);
    }

    @Override
    public FoodOrder getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_ORDER_GET_ONE, new FoodOrderMapper(),id);
    }
}
