package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.SttWorkRequestRecruitment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SttWorkRequestRecruitmentMapper implements RowMapper<SttWorkRequestRecruitment> {

    @Override
    public SttWorkRequestRecruitment mapRow(ResultSet resultSet, int i) throws SQLException {
        return SttWorkRequestRecruitment.builder()
                .sttWorkRequestRecruitmentId(resultSet.getInt("stt_work_request_recruitment_id"))
                .sttWorkRequestRecruitmentName(resultSet.getString("stt_work_request_recruitment_name"))
                .build();
    }
}
