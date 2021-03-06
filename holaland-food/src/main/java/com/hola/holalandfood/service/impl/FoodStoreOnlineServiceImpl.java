package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.FoodStoreOnline;
import com.hola.holalandfood.repository.FoodStoreOnlineRepository;
import com.hola.holalandfood.service.FoodStoreOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodStoreOnlineServiceImpl implements FoodStoreOnlineService {

    private final FoodStoreOnlineRepository foodStoreOnlineRepository;

    @Autowired
    public FoodStoreOnlineServiceImpl(FoodStoreOnlineRepository foodStoreOnlineRepository) {
        this.foodStoreOnlineRepository = foodStoreOnlineRepository;
    }

    @Override
    public List<FoodStoreOnline> getAll() throws DataAccessException {
        return foodStoreOnlineRepository.getAll();
    }

    @Override
    public List<FoodStoreOnline> getAllByType(int typeId, int code) throws DataAccessException {
        return foodStoreOnlineRepository.getAllByType(typeId, code);
    }

    @Override
    public FoodStoreOnline getOne(int id) throws DataAccessException {
        return foodStoreOnlineRepository.getOne(id);
    }

    @Override
    public FoodStoreOnline getOneByUserId(int userId) throws DataAccessException {
        return foodStoreOnlineRepository.getOneByUserId(userId);
    }

    @Override
    public FoodStoreOnline getOneByOrderId(int orderId) throws DataAccessException {
        return foodStoreOnlineRepository.getOneByOrderId(orderId);
    }

    @Override
    public boolean checkUserIsOwner(int userId, int storeId) throws DataAccessException {
        return foodStoreOnlineRepository.checkUserIsOwner(userId, storeId);
    }

    @Override
    public boolean updateShopInfo(FoodStoreOnline obj) throws DataAccessException {
        return foodStoreOnlineRepository.updateShopInfo(obj);
    }

    @Override
    public boolean isShopStopSelling(FoodStoreOnline obj) throws DataAccessException {
        return foodStoreOnlineRepository.isShopStopSelling(obj);
    }

    @Override
    public boolean isShopPauseSelling(FoodStoreOnline obj) throws DataAccessException {
        return foodStoreOnlineRepository.isShopPauseSelling(obj);
    }
}
