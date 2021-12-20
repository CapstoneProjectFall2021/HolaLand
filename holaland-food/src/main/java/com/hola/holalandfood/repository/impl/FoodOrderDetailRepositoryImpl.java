package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.mapper.FoodOrderDetailMapper;
import com.hola.holalandfood.repository.FoodOrderDetailRepository;
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
    public List<FoodOrderDetail> getAllByOrderId(int orderId) throws DataAccessException {
        return jdbcTemplate.query(FOOD_ORDER_DETAIL_GET_ALL_BY_ORDER_ID, new FoodOrderDetailMapper(), orderId);
    }

    @Override
    public FoodOrderDetail getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_ORDER_DETAIL_GET_ONE,new FoodOrderDetailMapper(),id);
    }

    @Override
    public boolean save(List<FoodOrderDetail> obj) throws DataAccessException {
        int[] updateCounts = jdbcTemplate.batchUpdate(
                FOOD_ORDER_DETAIL_SAVE,
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setObject(1, obj.get(i).getFoodOrderId());
                        ps.setObject(2, obj.get(i).getFoodItemId());
                        ps.setObject(3, obj.get(i).getFoodItemName());
                        ps.setObject(4, obj.get(i).getFoodItemPrice());
                        ps.setObject(5, obj.get(i).getFoodItemQuantity());
                    }

                    @Override
                    public int getBatchSize() {
                        return obj.size();
                    }
                });
        return updateCounts.length > 0;
    }
}
