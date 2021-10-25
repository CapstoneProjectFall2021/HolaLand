package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodTypeMapper implements RowMapper<FoodType> {
    @Override
    public FoodType mapRow(ResultSet resultSet, int i) throws SQLException {

        return FoodType.builder()
                .foodTypeId(resultSet.getInt("food_type_id"))
                .foodTypeIcon(resultSet.getString("food_type_icon"))
                .foodTypeName(resultSet.getString("food_type_name"))
                .foodTypeCount(resultSet.getInt("food_type_count"))
                .foodTypeDeleted(resultSet.getBoolean("food_type_deleted"))
                .build();
    }
}
