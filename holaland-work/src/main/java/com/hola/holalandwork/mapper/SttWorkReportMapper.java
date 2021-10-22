package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.SttWorkReport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SttWorkReportMapper implements RowMapper<SttWorkReport> {

    @Override
    public SttWorkReport mapRow(ResultSet resultSet, int i) throws SQLException {

        return SttWorkReport.builder()
                .sttWorkReportId(resultSet.getInt("stt_work_report_id"))
                .sttWorkReportContent(resultSet.getString("stt_work_report_content"))
                .build();
    }
}
