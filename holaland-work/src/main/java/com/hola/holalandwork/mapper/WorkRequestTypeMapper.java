package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkRequestType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkRequestTypeMapper implements RowMapper<com.hola.holalandwork.entity.WorkRequestType> {

    @Override
    public com.hola.holalandwork.entity.WorkRequestType mapRow(ResultSet resultSet, int i) throws SQLException {
        return com.hola.holalandwork.entity.WorkRequestType.builder()
                .workRequestTypeId(resultSet.getInt("work_request_type_id"))
                .workRequestTypeIcon(resultSet.getString("work_request_type_icon"))
                .workRequestTypeName(resultSet.getString("work_request_type_name"))
                .workRequestTypeCountRequestRecruitment(resultSet.getInt("work_request_type_count_request_recruitment"))
                .workRequestTypeCountRequestFindJob(resultSet.getInt("work_request_type_count_request_find_job"))
                .workRequestTypeDeleted(resultSet.getBoolean("work_request_type_deleted"))
                .build();
    }
}
