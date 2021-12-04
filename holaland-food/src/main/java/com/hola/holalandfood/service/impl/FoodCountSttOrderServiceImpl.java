package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.repository.FoodCountSttOrderRepository;
import com.hola.holalandfood.service.FoodCountSttOrderService;
import com.hola.holalandfood.view.FoodCountSttOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class FoodCountSttOrderServiceImpl implements FoodCountSttOrderService {

    private final FoodCountSttOrderRepository foodCountSttOrderRepository;

    @Autowired
    public FoodCountSttOrderServiceImpl(FoodCountSttOrderRepository foodCountSttOrderRepository) {
        this.foodCountSttOrderRepository = foodCountSttOrderRepository;
    }

    @Override
    public FoodCountSttOrder getCountSttOrderSeller(int id) throws DataAccessException {
        return foodCountSttOrderRepository.getCountSttOrderSeller(id);
    }

    @Override
    public FoodCountSttOrder getCountSttOrderStudent(int user_id) throws DataAccessException {
        return foodCountSttOrderRepository.getCountSttOrderStudent(user_id);
    }
}
