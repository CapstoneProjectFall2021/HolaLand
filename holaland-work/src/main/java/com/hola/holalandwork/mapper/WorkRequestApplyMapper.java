package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkRequestApply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkRequestApplyMapper implements RowMapper<WorkRequestApply> {

    @Override
    public WorkRequestApply mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkRequestApply.builder()
                .workRequestApplyId(resultSet.getInt("work_request_apply_id"))
                .userId(resultSet.getInt("user_id"))
                .workRequestRecruitmentId(resultSet.getInt("work_request_recruitment_id"))
                .sttWorkCode(resultSet.getInt("stt_work_code"))
                .workRequestApplyDeleted(resultSet.getBoolean("work_request_apply_deleted"))
                .build();
    }
}
