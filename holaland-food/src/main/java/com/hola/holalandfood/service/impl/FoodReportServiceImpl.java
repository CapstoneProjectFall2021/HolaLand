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
    public FoodReport getOne(int id) throws DataAccessException {
        return foodReportRepository.getOne(id);
    }
}
