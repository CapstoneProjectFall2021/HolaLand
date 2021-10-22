package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.SttWorkRequestBook;
import com.hola.holalandwork.mapper.SttWorkRequestBookMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.SttWorkRequestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SttWorkRequestBookRepositoryImpl implements SttWorkRequestBookRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SttWorkRequestBookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SttWorkRequestBook> getAll() throws DataAccessException {
        return jdbcTemplate.query(STT_WORK_REQUEST_BOOK_GET_ALL, new SttWorkRequestBookMapper());
    }

    @Override
    public SttWorkRequestBook getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(STT_WORK_REQUEST_BOOK_GET_ONE, new SttWorkRequestBookMapper(), id);
    }
}
