package com.hola.holalandtraffic.service.impl;

import com.hola.holalandtraffic.entity.Bus;
import com.hola.holalandtraffic.repository.BusRepository;
import com.hola.holalandtraffic.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public List<Bus> getAll() throws DataAccessException {
        return busRepository.getAll();
    }

    @Override
    public Bus getOne(int id) throws DataAccessException {
        return busRepository.getOne(id);
    }
}
