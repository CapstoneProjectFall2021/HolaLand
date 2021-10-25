package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.SttFood;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SttFoodMapper implements RowMapper<SttFood> {
    @Override
    public SttFood mapRow(ResultSet resultSet, int i) throws SQLException {

        return SttFood.builder()
                .sttFoodId(resultSet.getInt("stt_food_id"))
                .sttFoodName(resultSet.getString("stt_food_name"))
                .sttFoodCode(resultSet.getInt("stt_food_code"))
                .sttFoodValue(resultSet.getString("stt_food_value"))
                .sttFoodIcon(resultSet.getString("stt_food_icon"))
                .sttFoodCount(resultSet.getInt("stt_food_count"))
                .build();
    }
}
