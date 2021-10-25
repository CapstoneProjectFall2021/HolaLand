package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodOrderDetail;
import com.hola.holalandfood.repository.FoodOrderDetailRepository;
import com.hola.holalandfood.service.FoodOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOrderDetailServiceImpl implements FoodOrderDetailService {

    private final FoodOrderDetailRepository foodOrderDetailRepository;

    @Autowired
    public FoodOrderDetailServiceImpl(FoodOrderDetailRepository foodOrderDetailRepository) {
        this.foodOrderDetailRepository = foodOrderDetailRepository;
    }

    @Override
    public List<FoodOrderDetail> getAll() throws DataAccessException {
        return foodOrderDetailRepository.getAll();
    }

    @Override
    public FoodOrderDetail getOne(int id) throws DataAccessException {
        return foodOrderDetailRepository.getOne(id);
    }
}
