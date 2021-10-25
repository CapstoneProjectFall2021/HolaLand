package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodStoreOnlineType;
import com.hola.holalandfood.repository.FoodStoreOnlineTypeRepository;
import com.hola.holalandfood.service.FoodStoreOnlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodStoreOnlineTypeServiceImpl implements FoodStoreOnlineTypeService {

    private final FoodStoreOnlineTypeRepository foodStoreOnlineTypeRepository;

    @Autowired
    public FoodStoreOnlineTypeServiceImpl(FoodStoreOnlineTypeRepository foodStoreOnlineTypeRepository) {
        this.foodStoreOnlineTypeRepository = foodStoreOnlineTypeRepository;
    }

    @Override
    public List<FoodStoreOnlineType> getAll() throws DataAccessException {
        return foodStoreOnlineTypeRepository.getAll();
    }

    @Override
    public FoodStoreOnlineType getOne(int id) throws DataAccessException {
        return foodStoreOnlineTypeRepository.getOne(id);
    }
}
