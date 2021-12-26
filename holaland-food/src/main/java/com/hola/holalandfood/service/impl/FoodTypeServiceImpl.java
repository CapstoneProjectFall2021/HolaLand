package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodType;
import com.hola.holalandfood.repository.FoodTypeRepository;
import com.hola.holalandfood.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

    private final FoodTypeRepository foodTypeRepository;

    @Autowired
    public FoodTypeServiceImpl(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    @Override
    public List<FoodType> getAll() throws DataAccessException {
        return foodTypeRepository.getAll();
    }

    @Override
    public List<FoodType> getAllByUserId(int userId) throws DataAccessException {
        return foodTypeRepository.getAllByUserId(userId);
    }

    @Override
    public FoodType getOne(int id) throws DataAccessException {
        return foodTypeRepository.getOne(id);
    }
}
