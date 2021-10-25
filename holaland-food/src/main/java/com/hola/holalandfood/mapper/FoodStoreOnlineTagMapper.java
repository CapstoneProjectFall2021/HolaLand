package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodStoreOnlineTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodStoreOnlineTagMapper implements RowMapper<FoodStoreOnlineTag> {
    @Override
    public FoodStoreOnlineTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodStoreOnlineTag.builder()
                .foodStoreOnlineTagId(rs.getInt("food_store_online_tag_id"))
                .foodStoreOnlineId(rs.getInt("food_store_online_id"))
                .foodTagId(rs.getInt("food_tag_id"))
                .build();
    }
}
