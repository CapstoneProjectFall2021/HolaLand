package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodStoreOnlineRate;
import com.hola.holalandfood.repository.FoodStoreOnlineRateRepository;
import com.hola.holalandfood.service.FoodStoreOnlineRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodStoreOnlineRateServiceImpl implements FoodStoreOnlineRateService {

    private final FoodStoreOnlineRateRepository foodStoreOnlineRateRepository;

    @Autowired
    public FoodStoreOnlineRateServiceImpl(FoodStoreOnlineRateRepository foodStoreOnlineRateRepository) {
        this.foodStoreOnlineRateRepository = foodStoreOnlineRateRepository;
    }

    @Override
    public List<FoodStoreOnlineRate> getAll() throws DataAccessException {
        return foodStoreOnlineRateRepository.getAll();
    }

    @Override
    public FoodStoreOnlineRate getOne(int id) throws DataAccessException {
        return foodStoreOnlineRateRepository.getOne(id);
    }
}
