package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodOrderMapper implements RowMapper<FoodOrder> {
    @Override
    public FoodOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodOrder.builder()
                .foodOrderId(rs.getInt("food_order_id"))
                .userId(rs.getInt("user_id"))
                .foodStoreOnlineId(rs.getInt("food_store_online_id"))
                .sttFoodCode(rs.getInt("stt_food_code"))
                .foodOrderTotalPrice(rs.getInt("food_order_total_price"))
                .foodOrderCreatedDate(rs.getDate("food_order_created_date"))
                .foodOrderNote(rs.getString("food_order_note"))
                .foodOrderReasonReject(rs.getString("food_order_reason_reject"))
                .foodOrderDeleted(rs.getBoolean("food_order_deleted"))
                .build();
    }
}
