package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestBook;
import com.hola.holalandwork.mapper.WorkRequestBookMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkRequestBookRepositoryImpl implements WorkRequestBookRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestBookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestBook> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_BOOK_GET_ALL, new WorkRequestBookMapper());
    }

    @Override
    public List<WorkRequestBook> getAllByRequestId(int id) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_BOOK_GET_ALL_BY_REQUEST_ID, new WorkRequestBookMapper(), id);
    }

    @Override
    public WorkRequestBook getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_BOOK_GET_ONE, new WorkRequestBookMapper(), id);
    }

    @Override
    public boolean checkUserIsBooked(int userId, int findJobId) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_BOOK_CHECK_EXIST, Boolean.class, userId, findJobId);
    }

    @Override
    public boolean updateStatusRequestByUserIdAndFindJobId(WorkRequestBook obj) throws DataAccessException {
        return jdbcTemplate.update(
                WORK_REQUEST_BOOK_UPDATE_STT_ONE,
                obj.getSttWorkCode(),
                obj.getUserId(),
                obj.getWorkRequestFindJobId()
        ) > 0;
    }

    @Override
    public boolean rejectAllRequestByFindJobId(WorkRequestBook obj) throws DataAccessException {
        return jdbcTemplate.update(
                WORK_REQUEST_BOOK_REJECT_STT_ALL,
                3,
                obj.getWorkRequestFindJobId()
        ) > 0;
    }

    @Override
    public boolean save(WorkRequestBook obj) throws DataAccessException {
        return jdbcTemplate.update(
                INSERT_WORK_REQUEST_BOOK,
                obj.getUserId(),
                obj.getWorkRequestFindJobId(),
                obj.getSttWorkCode(),
                obj.isWorkRequestBookDeleted()
        ) > 0;
    }
}
