package com.hola.holalandfood.mapper;

import com.hola.holalandfood.entity.FoodReport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodReportMapper implements RowMapper<FoodReport> {
    @Override
    public FoodReport mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FoodReport.builder()
                .foodReportId(rs.getInt("food_report_id"))
                .userId(rs.getInt("user_id"))
                .foodOrderId(rs.getInt("food_order_id"))
                .foodReportContent(rs.getString("food_report_content"))
                .foodReportCreateDate(rs.getTimestamp("food_report_create_date"))
                .foodReportUpdateDate(rs.getTimestamp("food_report_update_date"))
                .foodReportDeleted(rs.getBoolean("food_report_deleted"))
                .build();
    }
}
