package com.hola.holalandfptu.service.impl;

import com.hola.holalandfptu.entity.ClubType;
import com.hola.holalandfptu.repository.ClubTypeRepository;
import com.hola.holalandfptu.service.ClubTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubTypeServiceImpl implements ClubTypeService {

    private final ClubTypeRepository clubTypeRepository;

    @Autowired
    public ClubTypeServiceImpl(ClubTypeRepository clubTypeRepository) {
        this.clubTypeRepository = clubTypeRepository;
    }

    @Override
    public List<ClubType> getAll() throws DataAccessException {
        return clubTypeRepository.getAll();
    }

    @Override
    public ClubType getOne(int id) throws DataAccessException {
        return clubTypeRepository.getOne(id);
    }
}
