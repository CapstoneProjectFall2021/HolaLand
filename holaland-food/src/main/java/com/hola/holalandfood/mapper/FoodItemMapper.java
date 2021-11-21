package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodItemMapper implements RowMapper<FoodItem> {
    @Override
    public FoodItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodItem.builder()
                .foodItemId(rs.getInt("food_item_id"))
                .foodStoreOnlineId(rs.getInt("food_store_online_id"))
                .foodTagId(rs.getInt("food_tag_id"))
                .foodTypeId(rs.getInt("food_type_id"))
                .foodItemImage(rs.getString("food_item_image"))
                .foodItemName(rs.getString("food_item_name"))
                .foodItemPrice(rs.getInt("food_item_price"))
                .foodItemSoldNumber(rs.getInt("food_item_sold_number"))
                .foodItemIsActive(rs.getBoolean("food_item_is_active"))
                .foodItemDeleted(rs.getBoolean("food_item_deleted"))
                .build();
    }
}
