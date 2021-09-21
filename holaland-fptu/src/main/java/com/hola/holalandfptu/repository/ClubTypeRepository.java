package com.hola.holalandfptu.repository;

import com.hola.holalandfptu.entity.ClubType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ClubTypeRepository {

    List<ClubType> getAll() throws DataAccessException;

    ClubType getOne(int id) throws DataAccessException;
}
