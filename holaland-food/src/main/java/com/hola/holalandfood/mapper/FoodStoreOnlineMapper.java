package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodStoreOnline;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodStoreOnlineMapper implements RowMapper<FoodStoreOnline> {
    @Override
    public FoodStoreOnline mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodStoreOnline.builder()
                .foodStoreOnlineId(rs.getInt("food_store_online_id"))
                .userId(rs.getInt("user_id"))
                .foodStoreTypeId(rs.getInt("food_store_type_id"))
                .sttFoodCode(rs.getInt("stt_food_code"))
                .foodStoreOnlineImage(rs.getString("food_store_online_image"))
                .foodStoreOnlineName(rs.getString("food_store_online_name"))
                .foodStoreRate(rs.getInt("food_store_rate"))
                .foodStoreMinPrice(rs.getInt("food_store_min_price"))
                .foodStoreMaxPrice(rs.getInt("food_store_max_price"))
                .foodStoreDescription(rs.getString("food_store_description"))
                .foodStoreOnlineDeleted(rs.getBoolean("food_store_online_deleted"))
                .build();
    }
}
