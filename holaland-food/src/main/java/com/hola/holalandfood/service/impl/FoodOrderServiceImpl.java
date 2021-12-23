package com.hola.holalandfood.service.impl;

import com.hola.holalandcore.util.Calendars;
import com.hola.holalandfood.entity.FoodOrder;
import com.hola.holalandfood.repository.FoodOrderRepository;
import com.hola.holalandfood.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<FoodOrder> getAllByStoreOnlineId(int id) throws DataAccessException {
        return foodOrderRepository.getAllByStoreOnlineId(id);
    }

    @Override
    public List<FoodOrder> getAllUserOrderByUserIdAndStatus(int userId, Integer... status) throws DataAccessException {
        return foodOrderRepository.getAllUserOrderByUserIdAndStatus(userId, status);
    }

    @Override
    public List<FoodOrder> getAllSellerOrderByUserIdAndStatus(int userId, Integer... status) throws DataAccessException {
        return foodOrderRepository.getAllSellerOrderByUserIdAndStatus(userId, status);
    }

    @Override
    public FoodOrder getOne(int id) throws DataAccessException {
        return foodOrderRepository.getOne(id);
    }

    @Override
    public int save(FoodOrder obj) throws DataAccessException {
        return foodOrderRepository.save(obj);
    }

    @Override
    public boolean checkUserOrder(int storeId, int userId) throws DataAccessException {
        return foodOrderRepository.checkUserOrder(storeId, userId);
    }

    @Override
    public boolean updateSttFood(FoodOrder obj) throws DataAccessException {
        return foodOrderRepository.updateSttFood(obj);
    }

    @Override
    public boolean addReasonReject(FoodOrder obj) throws DataAccessException {
        return foodOrderRepository.addReasonReject(obj);
    }

    // Statistic
    @Override
    public List<Double> getListMoneyOfDayOfTheMonth(int month, int year, int storeId) {
        List<Double> listMoneyOfDayOfTheMonth = new ArrayList<>();
        int days = Calendars.getNumberOfDateInMonth(month, year);
        long temp = Calendars.getFirstDateOfMonth(month);

        for (int i = 1; i <= days; ++i) {
            double money = totalMoneyOfDay(temp, temp + 86400, storeId);
            listMoneyOfDayOfTheMonth.add(money);
            temp += 86400;
        }
        return listMoneyOfDayOfTheMonth;
    }

    @Override
    public List<Double> getListNumberOfOrdersOfDay(int month, int year, int storeId) {
        List<Double> listNumberOfOrdersOfDay = new ArrayList<>();
        int days = Calendars.getNumberOfDateInMonth(month, year);
        long temp = Calendars.getFirstDateOfMonth(month);

        for (int i = 1; i <= days; ++i) {
            listNumberOfOrdersOfDay.add((double) foodOrderRepository.getAllFoodOrderByDay(temp, temp + 86400, storeId).size());
            temp += 86400;
        }
        return listNumberOfOrdersOfDay;
    }

    public double totalMoneyOfDay(long startOfDay, long endOfDay, int storeId) {
        List<FoodOrder> listFoodOrder = foodOrderRepository.getAllFoodOrderByDay(startOfDay, endOfDay, storeId);
        double totalMoney = 0;

        for (FoodOrder o : listFoodOrder) {
            totalMoney += o.getFoodOrderTotalPrice();
        }
        return totalMoney;
    }
}
