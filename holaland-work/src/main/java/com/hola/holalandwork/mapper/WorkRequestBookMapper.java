package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkRequestBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkRequestBookMapper implements RowMapper<WorkRequestBook> {

    @Override
    public WorkRequestBook mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkRequestBook.builder()
                .workRequestBookId(resultSet.getInt("work_request_book_id"))
                .userId(resultSet.getInt("user_id"))
                .workRequestFindJobId(resultSet.getInt("work_request_find_job_id"))
                .sttWorkRequestBookId(resultSet.getInt("stt_work_request_book_id"))
                .workRequestBookDeleted(resultSet.getBoolean("work_request_book_deleted"))
                .build();
    }
}
