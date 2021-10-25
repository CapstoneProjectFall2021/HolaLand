package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodTagMapper implements RowMapper<FoodTag> {
    @Override
    public FoodTag mapRow(ResultSet resultSet, int i) throws SQLException {

        return FoodTag.builder()
                .foodTagId(resultSet.getInt("food_tag_id"))
                .foodTagName(resultSet.getString("food_tag_name"))
                .build();
    }
}
