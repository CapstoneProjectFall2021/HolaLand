package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.mapper.FoodOrderMapper;
import com.hola.holalandfood.repository.FoodOrderRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
    public List<FoodOrder> getAllByStoreOnlineId(int id) throws DataAccessException {
        return jdbcTemplate.query(FOOD_ORDER_GET_ALL_BY_STORE_ONLINE_ID, new FoodOrderMapper(), id);
    }

    @Override
    public List<FoodOrder> getAllUserOrderByUserIdAndStatus(int userId, Integer... status) throws DataAccessException {
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
    public List<FoodOrder> getAllSellerOrderByUserIdAndStatus(int userId, Integer... status) throws DataAccessException {
        StringBuilder s = new StringBuilder("(");
        for (int i = 0; i < status.length; i++) {
            s.append((i != status.length - 1) ? status[i] + "," : status[i] + ")");
        }
        String sql = "SELECT " +
                " T1.food_order_id," +
                " T1.user_id," +
                " T1.food_store_online_id," +
                " T1.stt_food_code," +
                " T1.food_order_total_price," +
                " T1.food_order_created_date," +
                " T1.food_order_note," +
                " T1.food_order_reason_reject," +
                " T1.food_order_deleted" +
                " FROM food_order T1" +
                " LEFT JOIN food_store_online T2" +
                " ON T1.food_store_online_id = T2.food_store_online_id" +
                " WHERE T2.user_id = ?" +
                " AND T1.stt_food_code in "+ s +
                " AND T1.food_order_deleted = 0" +
                " ORDER BY T1.food_order_created_date DESC";
        return jdbcTemplate.query(sql, new FoodOrderMapper(), userId);
    }

    @Override
    public FoodOrder getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_ORDER_GET_ONE, new FoodOrderMapper(),id);
    }

    @Override
    public int save(FoodOrder obj) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(FOOD_ORDER_SAVE, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, obj.getUserId());
            ps.setObject(2, obj.getFoodStoreOnlineId());
            ps.setObject(3, obj.getSttFoodCode());
            ps.setObject(4, obj.getFoodOrderTotalPrice());
            ps.setObject(5, obj.getFoodOrderCreatedDate());
            ps.setObject(6, obj.getFoodOrderNote());
            ps.setObject(7, obj.getFoodOrderReasonReject());
            ps.setObject(8, obj.isFoodOrderDeleted());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public boolean checkUserOrder(int storeId, int userId) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_ORDER_CHECK_USER_ORDER, Boolean.class, userId, storeId);
    }

    @Override
    public boolean updateSttFood(FoodOrder obj) throws DataAccessException {
        return jdbcTemplate.update(
                FOOD_ORDER_UPDATE_STT_FOOD,
                obj.getSttFoodCode(),
                obj.getFoodOrderId()
        ) > 0;
    }

    @Override
    public boolean addReasonReject(FoodOrder obj) throws DataAccessException {
        return jdbcTemplate.update(
                FOOD_ORDER_REJECT_ONE,
                obj.getFoodOrderReasonReject(),
                obj.getSttFoodCode(),
                obj.getFoodOrderId()
        ) > 0;
    }

}
