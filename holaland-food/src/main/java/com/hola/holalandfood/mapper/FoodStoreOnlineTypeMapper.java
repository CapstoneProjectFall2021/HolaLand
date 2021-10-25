package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodStoreOnlineType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodStoreOnlineTypeMapper implements RowMapper<FoodStoreOnlineType> {

    @Override
    public FoodStoreOnlineType mapRow(ResultSet resultSet, int i) throws SQLException {

        return FoodStoreOnlineType.builder()
                .foodStoreOnlineTypeId(resultSet.getInt("food_store_online_type_id"))
                .foodStoreOnlineId(resultSet.getInt("food_store_online_id"))
                .foodTypeId(resultSet.getInt("food_type_id"))
                .build();
    }
}
