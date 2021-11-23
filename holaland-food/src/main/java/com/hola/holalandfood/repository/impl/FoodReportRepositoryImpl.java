package com.hola.holalandfood.repository.impl;

import com.hola.holalandfood.entity.FoodReport;
import com.hola.holalandfood.mapper.FoodReportMapper;
import com.hola.holalandfood.mapper.FoodStoreOnlineRateMapper;
import com.hola.holalandfood.repository.FoodReportRepository;
import com.hola.holalandfood.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodReportRepositoryImpl implements FoodReportRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodReportRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FoodReport> getAll() throws DataAccessException {
        return jdbcTemplate.query(FOOD_REPORT_GET_ALL,new FoodReportMapper());
    }

    @Override
    public FoodReport getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(FOOD_REPORT_GET_ONE,new FoodReportMapper(),id);
    }
}