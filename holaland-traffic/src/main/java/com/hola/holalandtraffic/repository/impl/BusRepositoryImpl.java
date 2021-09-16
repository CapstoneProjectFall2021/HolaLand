package com.hola.holalandtraffic.repository.impl;

import com.hola.holalandtraffic.entity.Bus;
import com.hola.holalandtraffic.mapper.BusMapper;
import com.hola.holalandtraffic.repository.BusRepository;
import com.hola.holalandtraffic.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusRepositoryImpl implements BusRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BusRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Bus> getAll() throws DataAccessException {
        return jdbcTemplate.query(BUS_GET_ALL, new BusMapper());
    }

    @Override
    public Bus getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(BUS_GET_ONE, new BusMapper(), id);
    }
}
