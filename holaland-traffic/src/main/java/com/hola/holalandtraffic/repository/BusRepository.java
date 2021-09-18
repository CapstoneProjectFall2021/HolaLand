package com.hola.holalandtraffic.repository;

import com.hola.holalandtraffic.entity.Bus;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BusRepository {

    List<Bus> getAll() throws DataAccessException;

    Bus getOne(int id) throws DataAccessException;
}
