package com.hola.holalandtraffic.repository.impl;

import com.hola.holalandtraffic.entity.MotorbikeTaxiDrivers;
import com.hola.holalandtraffic.mapper.MotorbikeTaxiDriversMapper;
import com.hola.holalandtraffic.repository.IRepositoryQuery;
import com.hola.holalandtraffic.repository.MotorbikeTaxiDriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorbikeTaxiDriversRepositoryImpl implements MotorbikeTaxiDriversRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MotorbikeTaxiDriversRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MotorbikeTaxiDrivers> getAll() throws DataAccessException {
        return jdbcTemplate.query(MOTORBIKE_TAXI_DRIVERS_GET_ALL, new MotorbikeTaxiDriversMapper());
    }

    @Override
    public MotorbikeTaxiDrivers getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(MOTORBIKE_TAXI_DRIVERS_GET_ONE, new MotorbikeTaxiDriversMapper(), id);
    }
}
