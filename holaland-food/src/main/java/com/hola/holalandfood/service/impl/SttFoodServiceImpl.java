package com.hola.holalandfood.service.impl;

import com.hola.holalandfood.entity.SttFood;
import com.hola.holalandfood.repository.SttFoodRepository;
import com.hola.holalandfood.service.SttFoodService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SttFoodServiceImpl implements SttFoodService {

    private final SttFoodRepository sttFoodRepository;

    public SttFoodServiceImpl(SttFoodRepository sttFoodRepository) {
        this.sttFoodRepository = sttFoodRepository;
    }

    @Override
    public List<SttFood> getAll() throws DataAccessException {
        return sttFoodRepository.getAll();
    }

    @Override
    public SttFood getOne(int id) throws DataAccessException {
        return sttFoodRepository.getOne(id);
    }
}
