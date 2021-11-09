package com.hola.holalandcore.mapper;

import com.hola.holalandcore.entity.UserDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailMapper implements RowMapper<UserDetail> {

    @Override
    public UserDetail mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return UserDetail.builder()
                .userDetailName(resultSet.getString("user_name"))
                .userDetailDob(resultSet.getString("user_dob"))
                .userDetailGender(resultSet.getInt("user_gender"))
                .userDetailPhone(resultSet.getString("user_phone"))
                .build();
    }
}
