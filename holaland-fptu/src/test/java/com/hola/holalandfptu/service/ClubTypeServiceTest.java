package com.hola.holalandfptu.service;

import com.hola.holalandfptu.entity.ClubType;
import com.hola.holalandfptu.repository.ClubTypeRepository;
import com.hola.holalandfptu.service.impl.ClubTypeServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClubTypeServiceTest {
    @InjectMocks
    ClubTypeServiceImpl clubTypeService;

    @Mock
    ClubTypeRepository clubTypeRepository;

    public ClubType genClubType(){
        ClubType clubType = ClubType.builder()
                .fptuClubTypeId(1)
                .fptuClubTypeIcon("icon.jpg")
                .fptuClubTypeName("Học thuật")
                .fptuClubTypeCount(3)
                .fptuClubTypeDeleted(false)
                .build();
        return clubType;
    }

    @Test
    public void getAllClubType() throws Exception {
        List<ClubType> clubTypeList = new ArrayList<>();
        clubTypeList.add(genClubType());
        when(clubTypeRepository.getAll()).thenReturn(clubTypeList);
        clubTypeService.getAll();
    }

    @Test
    public void getOneClubType() throws Exception {
        when(clubTypeRepository.getOne(1)).thenReturn(genClubType());
        clubTypeRepository.getOne(1);
    }
}
