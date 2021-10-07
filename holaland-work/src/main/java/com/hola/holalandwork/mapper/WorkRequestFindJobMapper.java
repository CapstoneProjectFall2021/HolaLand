package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkRequestFindJobMapper implements RowMapper<WorkRequestFindJob> {

    @Override
    public WorkRequestFindJob mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkRequestFindJob.builder()
                .workRequestFindJobId(resultSet.getInt("work_request_find_job_id"))
                .accountId(resultSet.getInt("account_id"))
                .workJobTypeId(resultSet.getInt("work_job_type_id"))
                .workTimeId(resultSet.getInt("work_time_id"))
                .sttWorkRequestFindJobId(resultSet.getInt("stt_work_request_find_job_id"))
                .workRequestFindJobTitle(resultSet.getString("work_request_find_job_title"))
                .workRequestFindJobStartDateTime(resultSet.getString("work_request_find_job_start_date_time"))
                .workRequestFindJobEndDateTime(resultSet.getString("work_request_find_job_end_date_time"))
                .workRequestFindJobLastUpdateDateTime(resultSet.getString("work_request_find_job_last_update_date_time"))
                .workRequestFindJobDescription(resultSet.getString("work_request_find_job_description"))
                .workRequestFindJobPersonalExperience(resultSet.getString("work_request_find_job_personal_experience"))
                .workRequestFindJobDeleted(resultSet.getBoolean("work_request_find_job_deleted"))
                .build();
    }
}
