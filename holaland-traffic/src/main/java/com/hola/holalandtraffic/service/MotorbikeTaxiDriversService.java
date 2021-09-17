package com.hola.holalandtraffic.service;

import com.hola.holalandtraffic.entity.MotorbikeTaxiDrivers;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MotorbikeTaxiDriversService {

    List<MotorbikeTaxiDrivers> getAll() throws DataAccessException;

    MotorbikeTaxiDrivers getOne(int id) throws DataAccessException;
}
