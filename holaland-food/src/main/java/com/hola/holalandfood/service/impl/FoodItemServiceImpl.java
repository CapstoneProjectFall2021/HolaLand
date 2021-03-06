package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodItem;
import com.hola.holalandfood.repository.FoodItemRepository;
import com.hola.holalandfood.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public List<FoodItem> getAll() throws DataAccessException {
        return foodItemRepository.getAll();
    }

    @Override
    public List<FoodItem> getAllByStoreOnlineId(int id) throws DataAccessException {
        return foodItemRepository.getAllByStoreOnlineId(id);
    }

    @Override
    public List<FoodItem> getAllByStoreOnlineIdAndTagId(int id, int tagId) throws DataAccessException {
        return foodItemRepository.getAllByStoreOnlineIdAndTagId(id, tagId);
    }

    @Override
    public FoodItem getOne(int id) throws DataAccessException {
        return foodItemRepository.getOne(id);
    }

    @Override
    public int countItemSold(int id) throws DataAccessException {
        return foodItemRepository.countItemSold(id);
    }

    @Override
    public List<FoodItem> getAllByUserId(int userId) throws DataAccessException {
        return foodItemRepository.getAllByUserId(userId);
    }

    @Override
    public boolean deletedOne(FoodItem obj) throws DataAccessException {
        return foodItemRepository.deletedOne(obj);
    }

    @Override
    public boolean save(FoodItem foodItem) throws DataAccessException {
        return foodItemRepository.save(foodItem);
    }

    @Override
    public boolean update(FoodItem obj) throws DataAccessException {
        return foodItemRepository.update(obj);
    }

    @Override
    public List<FoodItem> search(String textSearch) throws DataAccessException {
        return foodItemRepository.search(textSearch);
    }
}
