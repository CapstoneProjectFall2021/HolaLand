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
        String sql = "SELECT\n" +
                "       T1.food_order_id,\n" +
                "       T1.user_id,\n" +
                "       T1.food_store_online_id,\n" +
                "       T1.stt_food_code,\n" +
                "       T1.food_order_total_price,\n" +
                "       T1.food_order_created_date,\n" +
                "       T1.food_order_note,\n" +
                "       T1.food_order_reason_reject,\n" +
                "       T1.food_order_deleted\n" +
                "FROM food_order T1\n" +
                "LEFT JOIN food_store_online T2\n" +
                "ON T1.food_store_online_id = T2.food_store_online_id\n" +
                "WHERE T2.user_id = ?\n" +
                "  AND T1.stt_food_code in "+ s +"\n" +
                "  AND T1.food_order_deleted = 0\n" +
                "ORDER BY T1.food_order_created_date DESC";
        return jdbcTemplate.query(sql, new FoodOrderMapper(), userId);
    }

    @Override
    public FoodOrder getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_ORDER_GET_ONE, new FoodOrderMapper(),id);
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
