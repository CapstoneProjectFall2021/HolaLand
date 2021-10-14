package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.SttWorkRequestApply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SttWorkRequestApplyMapper implements RowMapper<SttWorkRequestApply> {

    @Override
    public SttWorkRequestApply mapRow(ResultSet resultSet, int i) throws SQLException {
        return SttWorkRequestApply.builder()
                .sttWorkRequestApplyId(resultSet.getInt("stt_work_request_apply_id"))
                .sttWorkRequestApplyStatus(resultSet.getString("stt_work_request_apply_status"))
                .build();
    }
}
