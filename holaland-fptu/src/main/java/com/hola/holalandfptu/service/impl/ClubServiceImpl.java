package com.hola.holalandfptu.service.impl;

import com.hola.holalandfptu.entity.Club;
import com.hola.holalandfptu.repository.ClubRepository;
import com.hola.holalandfptu.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<Club> getAllByType(int type) throws DataAccessException {
        return clubRepository.getAllByType(type);
    }

    @Override
    public Club getOne(int id) throws DataAccessException {
        return clubRepository.getOne(id);
    }
}
