package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkRequestJobSave;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkRequestJobSaveMapper implements RowMapper<WorkRequestJobSave> {
    @Override
    public WorkRequestJobSave mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkRequestJobSave.builder()
                .workRequestJobSaveId(resultSet.getInt("work_request_jobs_save_id"))
                .accountId(resultSet.getInt("account_id"))
                .workRequestRecruitmentId(resultSet.getInt("work_request_recruitment_id"))
                .workRequestJobsSaveDeleted(resultSet.getBoolean("work_request_jobs_save_deleted"))
                .build();
    }
}
