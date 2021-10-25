package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodStoreOnlineRateMapper implements RowMapper<FoodStoreOnlineRate> {
    @Override
    public FoodStoreOnlineRate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodStoreOnlineRate.builder()
                .foodRateId(rs.getInt("food_rate_id"))
                .foodStoreOnlineId(rs.getInt("food_store_online_id"))
                .foodRatePoint(rs.getInt("food_rate_point"))
                .foodRateComment(rs.getString("food_rate_comment"))
                .build();
    }
}
