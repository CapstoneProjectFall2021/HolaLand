package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodStoreType;
import com.hola.holalandfood.repository.FoodStoreTypeRepository;
import com.hola.holalandfood.service.FoodStoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodStoreTypeServiceImpl implements FoodStoreTypeService {

    private final FoodStoreTypeRepository foodStoreTypeRepository;

    @Autowired
    public FoodStoreTypeServiceImpl(FoodStoreTypeRepository foodStoreTypeRepository) {
        this.foodStoreTypeRepository = foodStoreTypeRepository;
    }

    @Override
    public List<FoodStoreType> getAll() throws DataAccessException {
        return foodStoreTypeRepository.getAll();
    }

    @Override
    public FoodStoreType getOne(int id) throws DataAccessException {
        return foodStoreTypeRepository.getOne(id);
    }
}
