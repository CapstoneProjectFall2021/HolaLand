package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import com.hola.holalandfood.mapper.FoodStoreOnlineRateMapper;
import com.hola.holalandfood.repository.FoodStoreOnlineRateRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodStoreOnlineRateRepositoryImpl implements FoodStoreOnlineRateRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodStoreOnlineRateRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodStoreOnlineRate> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_STORE_ONLINE_RATE_GET_ALL, new FoodStoreOnlineRateMapper());
    }

    @Override
    public List<FoodStoreOnlineRate> getAllCommentByStoreOnlineId(int id) throws DataAccessException {
        return jdbcTemplate.query(FOOD_STORE_ONLINE_RATE_GET_ALL_BY_STORE_ONLINE_ID, new FoodStoreOnlineRateMapper(), id);
    }

    @Override
    public FoodStoreOnlineRate getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_STORE_ONLINE_RATE_GET_ONE, new FoodStoreOnlineRateMapper(), id);
    }

    @Override
    public FoodStoreOnlineRate getUserComment(int userId, int storeId) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_STORE_ONLINE_RATE_GET_COMMENT, new FoodStoreOnlineRateMapper(), userId, storeId);
    }

    @Override
    public boolean checkUserCommentExist(int userId, int storeId) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_STORE_ONLINE_RATE_CHECK_COMMENT_EXIST, Boolean.class, userId, storeId);
    }

    @Override
    public boolean save(FoodStoreOnlineRate obj) throws DataAccessException {
        return jdbcTemplate.update(
                FOOD_STORE_ONLINE_RATE_INSERT,
                obj.getUserId(),
                obj.getFoodStoreOnlineId(),
                obj.getFoodStoreOnlineRatePoint(),
                obj.getFoodStoreOnlineRateComment(),
                obj.getFoodStoreOnlineRateCreateTime(),
                obj.getFoodStoreOnlineRateUpdateTime(),
                obj.isFoodStoreOnlineDeleted()
        ) > 0;
    }

    @Override
    public boolean update(FoodStoreOnlineRate obj) throws DataAccessException {
        return jdbcTemplate.update(
                FOOD_STORE_ONLINE_RATE_UPDATE,
                obj.getFoodStoreOnlineRatePoint(),
                obj.getFoodStoreOnlineRateComment(),
                obj.getFoodStoreOnlineRateUpdateTime(),
                obj.getFoodStoreOnlineRateId()
        ) > 0;
    }
}
