package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkReport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkReportMapper implements RowMapper<WorkReport> {

    @Override
    public WorkReport mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkReport.builder()
                .workReportId(resultSet.getInt("work_report_id"))
                .sttWorkCode(resultSet.getInt("stt_work_code"))
                .workReportReasonId(resultSet.getInt("work_report_reason_id"))
                .workReportDescription(resultSet.getString("work_report_description"))
                .workReportDeleted(resultSet.getBoolean("work_report_deleted"))
                .build();
    }
}
