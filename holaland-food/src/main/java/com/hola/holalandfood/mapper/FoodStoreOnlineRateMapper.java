package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodStoreOnlineRateMapper implements RowMapper<FoodStoreOnlineRate> {
    @Override
    public FoodStoreOnlineRate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodStoreOnlineRate.builder()
                .foodStoreOnlineRateId(rs.getInt("food_store_online_rate_id"))
                .foodStoreOnlineId(rs.getInt("food_store_online_id"))
                .foodStoreOnlineRatePoint(rs.getInt("food_store_online_rate_point"))
                .foodStoreOnlineRateComment(rs.getString("food_store_online_rate_comment"))
                .build();
    }
}
