package com.hola.holalandfood.service;

import com.hola.holalandfood.entity.FoodReport;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FoodReportService {

    List<FoodReport> getAll() throws DataAccessException;

    List<FoodReport> getAllByOrderId(int id) throws DataAccessException;

    FoodReport getOne(int id) throws DataAccessException;

    boolean save(FoodReport obj) throws DataAccessException;

    FoodReport getUserReported(int userId, int orderId) throws DataAccessException;

    FoodReport getOrderReport(int orderId) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}
