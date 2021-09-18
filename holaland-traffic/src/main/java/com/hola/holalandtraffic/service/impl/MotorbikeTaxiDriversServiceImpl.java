package com.hola.holalandtraffic.service.impl;

import com.hola.holalandtraffic.entity.MotorbikeTaxiDrivers;
import com.hola.holalandtraffic.repository.MotorbikeTaxiDriversRepository;
import com.hola.holalandtraffic.service.MotorbikeTaxiDriversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorbikeTaxiDriversServiceImpl implements MotorbikeTaxiDriversService {

    private final MotorbikeTaxiDriversRepository motorbikeTaxiDriversRepository;

    @Autowired
    public MotorbikeTaxiDriversServiceImpl(MotorbikeTaxiDriversRepository motorbikeTaxiDriversRepository) {
        this.motorbikeTaxiDriversRepository = motorbikeTaxiDriversRepository;
    }

    @Override
    public List<MotorbikeTaxiDrivers> getAll() throws DataAccessException {
        return motorbikeTaxiDriversRepository.getAll();
    }

    @Override
    public MotorbikeTaxiDrivers getOne(int id) throws DataAccessException {
        return motorbikeTaxiDriversRepository.getOne(id);
    }
}
