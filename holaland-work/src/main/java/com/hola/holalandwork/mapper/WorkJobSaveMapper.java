package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkJobSave;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkJobSaveMapper implements RowMapper<WorkJobSave> {
    @Override
    public WorkJobSave mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkJobSave.builder()
                .workJobSaveId(resultSet.getInt("work_jobs_save_id"))
                .accountId(resultSet.getInt("account_id"))
                .workRequestRecruitmentId(resultSet.getInt("work_request_recruitment_id"))
                .workJobsSaveDeleted(resultSet.getBoolean("work_jobs_save_deleted"))
                .build();
    }
}
