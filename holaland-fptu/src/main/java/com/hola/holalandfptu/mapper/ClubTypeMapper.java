package com.hola.holalandfptu.mapper;

import com.hola.holalandfptu.entity.ClubType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubTypeMapper implements RowMapper<ClubType> {

    @Override
    public ClubType mapRow(ResultSet resultSet, int i) throws SQLException {

        ClubType clubType = ClubType.builder()
                .fptuClubTypeId(resultSet.getInt("fptu_club_type_id"))
                .fptuClubTypeIcon(resultSet.getString("fptu_club_type_icon"))
                .fptuClubTypeName(resultSet.getString("fptu_club_type_name"))
                .fptuClubTypeCount(resultSet.getInt("fptu_club_type_count"))
                .fptuClubTypeDeleted(resultSet.getBoolean("fptu_club_type_deleted"))
                .build();
        return clubType;
    }
}
