package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodStoreOnlineTag;
import com.hola.holalandfood.mapper.FoodStoreOnlineTagMapper;
import com.hola.holalandfood.repository.FoodStoreOnlineTagRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodStoreOnlineTagRepositoryImpl implements FoodStoreOnlineTagRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodStoreOnlineTagRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodStoreOnlineTag> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_STORE_ONLINE_TAG_GET_ALL,new FoodStoreOnlineTagMapper());
    }

    @Override
    public FoodStoreOnlineTag getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_STORE_ONLINE_TAG_GET_ONE,new FoodStoreOnlineTagMapper(),id);
    }
}
