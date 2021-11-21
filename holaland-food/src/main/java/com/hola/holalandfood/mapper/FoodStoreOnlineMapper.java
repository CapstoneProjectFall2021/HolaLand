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
                .foodStoreOnlineRate(rs.getInt("food_store_online_rate"))
                .foodStoreOnlineMinPrice(rs.getInt("food_store_online_min_price"))
                .foodStoreOnlineMaxPrice(rs.getInt("food_store_online_max_price"))
                .foodStoreOnlineDescription(rs.getString("food_store_online_description"))
                .foodStoreOnlineDeleted(rs.getBoolean("food_store_online_deleted"))
                .build();
    }
}
