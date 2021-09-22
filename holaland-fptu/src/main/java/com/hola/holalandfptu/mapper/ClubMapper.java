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
                .fptuClubNameEn(resultSet.getString("fptu_club_name_en"))
                .fptuClubDescription(resultSet.getString("fptu_club_description"))
                .fptuClubLogo(resultSet.getString("fptu_club_logo"))
                .fptuClubImage(resultSet.getString("fptu_club_image"))
                .fptuClubTypeId(resultSet.getInt("fptu_club_type_id"))
                .fptuClubFanpageName(resultSet.getString("fptu_club_fanpage_name"))
                .fptuClubFanpage(resultSet.getString("fptu_club_fanpage"))
                .fptuClubMemberQuantity(resultSet.getInt("fptu_club_member_quantity"))
                .fptuClubContactName(resultSet.getString("fptu_club_contact_name"))
                .fptuClubContactEmail(resultSet.getString("fptu_club_contact_email"))
                .fptuClubContactPhoneNumber(resultSet.getString("fptu_club_contact_phone_number"))
                .fptuClubAchievements(resultSet.getString("fptu_club_achievements"))
                .fptuClubStatus(resultSet.getString("fptu_club_status"))
                .fptuClubDeleted(resultSet.getBoolean("fptu_club_deleted"))
                .build();
        return club;
    }
}
