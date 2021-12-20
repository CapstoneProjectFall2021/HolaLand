package com.hola.holalandfptu.service;

import com.hola.holalandfptu.entity.Club;
import com.hola.holalandfptu.repository.ClubRepository;
import com.hola.holalandfptu.service.impl.ClubServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClubServiceTest {
    @InjectMocks
    ClubServiceImpl clubService;

    @Mock
    ClubRepository clubRepository;

    public Club genClub(){
        Club club = Club.builder()
                .fptuClubId(1)
                .fptuClubName("Melody")
                .fptuClubDescription("Clb nhạc")
                .fptuClubLogo("logo.png")
                .fptuClubTypeId(1)
                .fptuClubFanpageName("Melody")
                .fptuClubContactName("0123456")
                .fptuClubAchievements("top 1")
                .fptuClubDeleted(false).build();
        return club;
    }

    @Test
    public void getAllByTypeClub() throws Exception {
        List<Club> clubList = new ArrayList<>();
        clubList.add(genClub());
        when(clubRepository.getAllByType(1)).thenReturn(clubList);
        clubService.getAllByType(1);
    }

    @Test
    public void getOneClub() throws Exception {
        when(clubRepository.getOne(1)).thenReturn(genClub());
        clubRepository.getOne(1);
    }
}
