package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.SttWork;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SttWorkMapper implements RowMapper<SttWork> {

    @Override
    public SttWork mapRow(ResultSet resultSet, int i) throws SQLException {

        return SttWork.builder()
                .sttWorkId(resultSet.getInt("stt_work_id"))
                .sttWorkName(resultSet.getString("stt_work_name"))
                .sttWorkCode(resultSet.getInt("stt_work_code"))
                .sttWorkValue(resultSet.getString("stt_work_value"))
                .sttWorkIcon(resultSet.getString("stt_work_icon"))
                .sttWorkCount(resultSet.getInt("stt_work_count"))
                .build();
    }
}
