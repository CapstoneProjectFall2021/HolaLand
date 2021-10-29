package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.repository.FoodOrderRepository;
import com.hola.holalandfood.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;

    @Autowired
    public FoodOrderServiceImpl(FoodOrderRepository foodOrderRepository) {
        this.foodOrderRepository = foodOrderRepository;
    }

    @Override
    public List<FoodOrder> getAll() throws DataAccessException {
        return foodOrderRepository.getAll();
    }

    @Override
    public FoodOrder getOne(int id) throws DataAccessException {
        return foodOrderRepository.getOne(id);
    }
}