package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodStoreOnlineTag;
import com.hola.holalandfood.mapper.FoodStoreOnlineTagMapper;
import com.hola.holalandfood.repository.FoodStoreOnlineTagRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    @Override
    public boolean deleteAllTagByFoodStoreOnlineId(int userId) throws DataAccessException {
        return jdbcTemplate.update(DELETE_ALL_TAG_BY_FOOD_STORE_ONLINE_ID, userId) > 0;
    }

    @Override
    public int[] insertTagForFoodStore(List<FoodStoreOnlineTag> foodStoreOnlineTags) throws DataAccessException {
        return this.jdbcTemplate.batchUpdate(
                INSERT_ALL_TAG_BY_FOOD_STORE_ONLINE_ID,
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, foodStoreOnlineTags.get(i).getFoodStoreOnlineId());
                        ps.setInt(2, foodStoreOnlineTags.get(i).getFoodTagId());
                    }

                    public int getBatchSize() {
                        return foodStoreOnlineTags.size();
                    }

                });
    }
}
