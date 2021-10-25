package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodStoreType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodStoreTypeMapper implements RowMapper<FoodStoreType> {
    @Override
    public FoodStoreType mapRow(ResultSet resultSet, int i) throws SQLException {

        return FoodStoreType.builder()
                .foodStoreTypeId(resultSet.getInt("food_store_type_id"))
                .foodStoreTypeName(resultSet.getString("food_store_type_name"))
                .build();
    }
}
