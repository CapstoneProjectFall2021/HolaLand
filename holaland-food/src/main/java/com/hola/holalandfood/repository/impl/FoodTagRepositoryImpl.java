package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.mapper.FoodTagMapper;
import com.hola.holalandfood.repository.FoodTagRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodTagRepositoryImpl implements FoodTagRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodTagRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodTag> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_TAG_GET_ALL, new FoodTagMapper());
    }

    @Override
    public List<FoodTag> getAllByStoreOnlineId(int id) throws DataAccessException {
        return jdbcTemplate.query(FOOD_TAG_GET_ALL_BY_STORE_ONLINE_ID, new FoodTagMapper(), id);
    }

    @Override
    public FoodTag getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_TAG_GET_ONE, new FoodTagMapper(), id);
    }

    @Override
    public List<FoodTag> getAllByUserId(int userId) throws DataAccessException {
        return jdbcTemplate.query(FOOD_TAG_GET_ALL_BY_USER_ID,  new FoodTagMapper(), userId);
    }

    @Override
    public List<FoodTag> search(String textSearch) throws DataAccessException {
        return jdbcTemplate.query(FOOD_TAG_SEARCH,  new FoodTagMapper(), "%" + textSearch + "%");
    }
}
