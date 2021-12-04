package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.SttFood;
import com.hola.holalandfood.mapper.SttFoodMapper;
import com.hola.holalandfood.repository.IRepositoryQuery;
import com.hola.holalandfood.repository.SttFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SttFoodRepositoryImpl implements SttFoodRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SttFoodRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SttFood> getAll() throws DataAccessException {
        return jdbcTemplate.query(STT_FOOD_GET_ALL, new SttFoodMapper());
    }

    @Override
    public List<SttFood> getAllHistoryOrder() throws DataAccessException {
        return jdbcTemplate.query(STT_FOOD_GET_ALL_HISTORY_ORDER, new SttFoodMapper());
    }

    @Override
    public SttFood getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(STT_FOOD_GET_ONE, new SttFoodMapper(), id);
    }
}
