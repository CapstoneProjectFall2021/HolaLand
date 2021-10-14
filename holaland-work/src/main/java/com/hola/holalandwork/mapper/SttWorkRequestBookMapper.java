package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.SttWorkRequestBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SttWorkRequestBookMapper implements RowMapper<SttWorkRequestBook> {
    @Override
    public SttWorkRequestBook mapRow(ResultSet resultSet, int i) throws SQLException {
        return SttWorkRequestBook.builder()
                .sttWorkRequestBookId(resultSet.getInt("stt_work_request_book_id"))
                .sttWorkRequestBookStatus(resultSet.getString("stt_work_request_book_status"))
                .build();
    }
}
