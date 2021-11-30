package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodTag;
import com.hola.holalandfood.repository.FoodTagRepository;
import com.hola.holalandfood.service.FoodTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTagServiceImpl implements FoodTagService {

    private final FoodTagRepository foodTagRepository;

    @Autowired
    public FoodTagServiceImpl(FoodTagRepository foodTagRepository) {
        this.foodTagRepository = foodTagRepository;
    }

    @Override
    public List<FoodTag> getAll() throws DataAccessException {
        return foodTagRepository.getAll();
    }

    @Override
    public List<FoodTag> getAllByStoreOnlineId(int id) throws DataAccessException {
        return foodTagRepository.getAllByStoreOnlineId(id);
    }

    @Override
    public FoodTag getOne(int id) throws DataAccessException {
        return foodTagRepository.getOne(id);
    }

    @Override
    public List<FoodTag> getAllByUserId(int userId) throws DataAccessException {
        return foodTagRepository.getAllByUserId(userId);
    }

    @Override
    public List<FoodTag> search(String textSearch) throws DataAccessException {
        return foodTagRepository.search(textSearch);
    }
}
