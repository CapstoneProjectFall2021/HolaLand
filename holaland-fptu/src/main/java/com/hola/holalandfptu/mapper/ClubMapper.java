package com.hola.holalandfptu.mapper;

import com.hola.holalandfptu.entity.Club;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubMapper implements RowMapper<Club> {

    @Override
    public Club mapRow(ResultSet resultSet, int i) throws SQLException {

        Club club = Club.builder()
                .fptuClubId(resultSet.getInt("fptu_club_id"))
                .fptuClubName(resultSet.getString("fptu_club_name"))
                .fptuClubDescription(resultSet.getString("fptu_club_description"))
                .fptuClubLogo(resultSet.getString("fptu_club_logo"))
                .fptuClubType(resultSet.getInt("fptu_club_type"))
                .fptuClubFanpage(resultSet.getString("fptu_club_fanpage"))
                .fptuClubMemberQuantity(resultSet.getInt("fptu_club_member_quantity"))
                .fptuClubContactName(resultSet.getString("fptu_club_contact_name"))
                .fptuClubContactEmail(resultSet.getString("fptu_club_contact_email"))
                .fptuClubContactPhone(resultSet.getString("fptu_club_contact_phone_number"))
                .fptuClubAchievements(resultSet.getString("fptu_club_achievements"))
                .fptuClubDeleted(resultSet.getBoolean("fptu_club_deleted"))
                .build();
        return club;
    }
}
