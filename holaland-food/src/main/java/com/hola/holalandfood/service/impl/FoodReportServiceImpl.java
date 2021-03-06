package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodReport;
import com.hola.holalandfood.repository.FoodReportRepository;
import com.hola.holalandfood.service.FoodReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodReportServiceImpl implements FoodReportService {

    private final FoodReportRepository foodReportRepository;

    @Autowired
    public FoodReportServiceImpl(FoodReportRepository foodReportRepository) {
        this.foodReportRepository = foodReportRepository;
    }

    @Override
    public List<FoodReport> getAll() throws DataAccessException {
        return foodReportRepository.getAll();
    }

    @Override
    public List<FoodReport> getAllByOrderId(int id) throws DataAccessException {
        return foodReportRepository.getAllByOrderId(id);
    }

    @Override
    public FoodReport getOne(int id) throws DataAccessException {
        return foodReportRepository.getOne(id);
    }

    @Override
    public boolean save(FoodReport obj) throws DataAccessException {
        return foodReportRepository.save(obj);
    }

    @Override
    public FoodReport getUserReported(int userId, int orderId) throws DataAccessException {
        return foodReportRepository.getUserReported(userId, orderId);
    }

    @Override
    public FoodReport getOrderReport(int orderId) throws DataAccessException {
        return foodReportRepository.getOrderReport(orderId);
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return foodReportRepository.delete(id);
    }
}
