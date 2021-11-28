package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodStoreOnlineTag;
import com.hola.holalandfood.repository.FoodStoreOnlineTagRepository;
import com.hola.holalandfood.service.FoodStoreOnlineTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodStoreOnlineTagServiceImpl implements FoodStoreOnlineTagService {

    private final FoodStoreOnlineTagRepository foodStoreOnlineTagRepository;

    @Autowired
    public FoodStoreOnlineTagServiceImpl(FoodStoreOnlineTagRepository foodStoreOnlineTagRepository) {
        this.foodStoreOnlineTagRepository = foodStoreOnlineTagRepository;
    }

    @Override
    public List<FoodStoreOnlineTag> getAll() throws DataAccessException {
        return foodStoreOnlineTagRepository.getAll();
    }

    @Override
    public FoodStoreOnlineTag getOne(int id) throws DataAccessException {
        return foodStoreOnlineTagRepository.getOne(id);
    }

    @Override
    public boolean deleteAllTagByFoodStoreOnlineId(int userId) throws DataAccessException {
        return foodStoreOnlineTagRepository.deleteAllTagByFoodStoreOnlineId(userId);
    }

    @Override
    public int[] insertTagForFoodStore(List<FoodStoreOnlineTag> foodStoreOnlineTags) throws DataAccessException {
        return foodStoreOnlineTagRepository.insertTagForFoodStore(foodStoreOnlineTags);
    }
}
