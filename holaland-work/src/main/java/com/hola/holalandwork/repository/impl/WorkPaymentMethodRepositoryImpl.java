package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkPaymentMethod;
import com.hola.holalandwork.mapper.WorkPaymentMethodMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkPaymentMethodRepositoryImpl implements WorkPaymentMethodRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkPaymentMethodRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkPaymentMethod> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_PAYMENT_METHOD_GET_ALL, new WorkPaymentMethodMapper());
    }

    @Override
    public WorkPaymentMethod getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_PAYMENT_METHOD_GET_ONE, new WorkPaymentMethodMapper(), id);
    }
}
