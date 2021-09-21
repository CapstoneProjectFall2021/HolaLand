package com.hola.holalandfptu.service;

import com.hola.holalandfptu.entity.ClubType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ClubTypeService {

    List<ClubType> getAll() throws DataAccessException;

    ClubType getOne(int id) throws DataAccessException;
}
