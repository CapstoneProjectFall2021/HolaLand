package com.hola.holalandwork.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkRequestRecruitmentSavedMapper implements RowMapper<com.hola.holalandwork.entity.WorkRequestRecruitmentSaved> {
    @Override
    public com.hola.holalandwork.entity.WorkRequestRecruitmentSaved mapRow(ResultSet resultSet, int i) throws SQLException {
        return com.hola.holalandwork.entity.WorkRequestRecruitmentSaved.builder()
                .workRequestRecruitmentSavedId(resultSet.getInt("work_request_recruitment_saved_id"))
                .userId(resultSet.getInt("user_id"))
                .workRequestRecruitmentId(resultSet.getInt("work_request_recruitment_id"))
                .workRequestRecruitmentSavedDeleted(resultSet.getBoolean("work_request_recruitment_saved_deleted"))
                .build();
    }
}
