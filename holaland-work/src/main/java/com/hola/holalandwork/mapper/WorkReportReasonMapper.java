package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkReportReason;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkReportReasonMapper implements RowMapper<WorkReportReason> {
    @Override
    public WorkReportReason mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkReportReason.builder()
                .workReportReasonId(resultSet.getInt("work_report_reason_id"))
                .workReportReasonName(resultSet.getString("work_report_reason_name"))
                .build();
    }
}
