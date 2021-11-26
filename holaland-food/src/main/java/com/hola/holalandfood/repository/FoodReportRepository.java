package com.hola.holalandfood.repository;

import com.hola.holalandfood.entity.FoodReport;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodReportRepository {

    List<FoodReport> getAll() throws DataAccessException;

    List<FoodReport> getAllByOrderId(int id) throws DataAccessException;

    FoodReport getOne(int id) throws DataAccessException;
}
