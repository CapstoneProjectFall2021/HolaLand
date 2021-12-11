package com.hola.holalandweb.controller;

import com.hola.holalandfood.entity.*;
import com.hola.holalandfood.service.*;
import com.hola.holalandfptu.entity.Club;
import com.hola.holalandfptu.entity.ClubType;
import com.hola.holalandfptu.service.ClubService;
import com.hola.holalandfptu.service.ClubTypeService;
import com.hola.holalandweb.module.food.controller.FoodStoreController;
import com.hola.holalandweb.module.fptu.controller.FptUniversityController;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class FptUniversityControllerTest {
    @InjectMocks
    FptUniversityController fptUniversityController;

    private MockMvc mockMvc;

    @Mock
    ClubService clubService;

    @Mock
    ClubTypeService clubTypeService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(fptUniversityController)
                .build();
    }

    public ClubType setAttrClubType(){
        ClubType clubType = ClubType.builder()
                .fptuClubTypeId(1)
                .fptuClubTypeIcon("icon.jpg")
                .fptuClubTypeName("Học thuật")
                .fptuClubTypeCount(3)
                .fptuClubTypeDeleted(false)
                .build();
        return clubType;
    }

    public Club setAttrClub(){
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
    public void goToFptUniversityTest() throws Exception{
        mockMvc.perform(get("/fpt-university"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-fpt-university"));
    }

    @Test
    public void goToFptUniversityClubTest() throws Exception{
        List<ClubType> clubTypeList = new ArrayList<>();
        clubTypeList.add(setAttrClubType());
        List<Club> clubList = new ArrayList<>();
        clubList.add(setAttrClub());
        when(clubTypeService.getAll()).thenReturn(clubTypeList);
        when(clubService.getAllByType(1)).thenReturn(clubList);
        mockMvc.perform(get("/fpt-university/club"))
                .andExpect(status().isOk())
                .andExpect(view().name("module-fpt-university"));
    }

    @Test
    public void getFptUniversityClubTypeTest() throws Exception{
        List<ClubType> clubTypeList = new ArrayList<>();
        clubTypeList.add(setAttrClubType());
        List<Club> clubList = new ArrayList<>();
        clubList.add(setAttrClub());
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("clubTypeId", "1");
        when(clubTypeService.getAll()).thenReturn(clubTypeList);
        when(clubService.getAllByType(1)).thenReturn(clubList);
        mockMvc.perform(get("/fpt-university/club/type").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-fpt-university"));
    }

    @Test
    public void getFptUniversityClubDetailTest() throws Exception{
        List<ClubType> clubTypeList = new ArrayList<>();
        clubTypeList.add(setAttrClubType());
        List<Club> clubList = new ArrayList<>();
        clubList.add(setAttrClub());
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("clubTypeId", "1");
        requestParams.add("clubId", "1");
        requestParams.add("page", "1");
        when(clubTypeService.getAll()).thenReturn(clubTypeList);
        when(clubService.getAllByType(1)).thenReturn(clubList);
        when(clubService.getOne(1)).thenReturn(setAttrClub());
        mockMvc.perform(get("/fpt-university/club/detail").params(requestParams))
                .andExpect(status().isOk())
                .andExpect(view().name("module-fpt-university"));
    }

    
}
