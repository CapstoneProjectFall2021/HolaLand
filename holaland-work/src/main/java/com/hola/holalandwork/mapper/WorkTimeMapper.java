package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkTimeMapper implements RowMapper<WorkTime> {

    @Override
    public WorkTime mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkTime.builder()
                .workTimeId(resultSet.getInt("work_time_id"))
                .workTimeName(resultSet.getString("work_time_name"))
                .workTimeDeleted(resultSet.getBoolean("work_time_deleted"))
                .build();
    }
}
