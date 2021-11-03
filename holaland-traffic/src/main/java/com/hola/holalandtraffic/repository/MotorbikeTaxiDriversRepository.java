package com.hola.holalandtraffic.repository;

import com.hola.holalandtraffic.entity.MotorbikeTaxiDrivers;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MotorbikeTaxiDriversRepository {

    List<MotorbikeTaxiDrivers> getAll() throws DataAccessException;

    MotorbikeTaxiDrivers getOne(int id) throws DataAccessException;
    
}
