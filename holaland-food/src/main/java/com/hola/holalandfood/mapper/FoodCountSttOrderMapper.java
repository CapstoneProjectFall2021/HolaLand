package com.hola.holalandfood.mapper;

import com.hola.holalandfood.view.FoodCountSttOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodCountSttOrderMapper implements RowMapper<FoodCountSttOrder> {

    @Override
    public FoodCountSttOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodCountSttOrder.builder()
                .rejectOrder(rs.getInt("reject_order"))
                .completed(rs.getInt("completed"))
                .cancel(rs.getInt("cancel"))
                .build();
    }
}
