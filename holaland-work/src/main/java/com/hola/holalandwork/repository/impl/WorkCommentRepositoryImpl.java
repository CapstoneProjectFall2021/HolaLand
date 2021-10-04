package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkComment;
import com.hola.holalandwork.mapper.WorkCommentMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkCommentRepositoryImpl implements WorkCommentRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkCommentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkComment> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_COMMENT_GET_ALL, new WorkCommentMapper());
    }

    @Override
    public WorkComment getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_COMMENT_GET_ONE, new WorkCommentMapper(), id);
    }
}
