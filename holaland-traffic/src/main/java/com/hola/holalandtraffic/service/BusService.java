package com.hola.holalandtraffic.service;

import com.hola.holalandtraffic.entity.Bus;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BusService {

    List<Bus> getAll() throws DataAccessException;

    Bus getOne(int id) throws DataAccessException;
}
