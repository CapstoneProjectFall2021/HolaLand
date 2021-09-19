package com.hola.holalandfptu.service;

import com.hola.holalandfptu.entity.Club;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ClubService {

    List<Club> getAllByType(int type) throws DataAccessException;
    Club getOne(int id) throws DataAccessException;
}
