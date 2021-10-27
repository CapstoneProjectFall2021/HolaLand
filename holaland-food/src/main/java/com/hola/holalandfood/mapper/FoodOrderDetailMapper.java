package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodOrderDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodOrderDetailMapper implements RowMapper<FoodOrderDetail> {
    @Override
    public FoodOrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodOrderDetail.builder()
                .foodOrderDetailId(rs.getInt("food_order_detail_id"))
                .foodOrderId(rs.getInt("food_order_id"))
                .foodItemId(rs.getInt("food_item_id"))
                .foodItemName(rs.getString("food_item_name"))
                .foodItemPrice(rs.getInt("food_item_price"))
                .foodItemQuantity(rs.getInt("food_item_quantity"))
                .build();
    }
}
