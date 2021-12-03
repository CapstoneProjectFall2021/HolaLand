package com.hola.holalandcore.mapper;

import com.hola.holalandcore.entity.UserDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailMapper implements RowMapper<UserDetail> {

    @Override
    public UserDetail mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return UserDetail.builder()
                .userDetailId(resultSet.getInt("user_detail_id"))
                .userId(resultSet.getInt("user_id"))
                .userDetailName(resultSet.getString("user_name"))
                .userDetailDob(resultSet.getDate("user_dob"))
                .userDetailGender(resultSet.getInt("user_gender"))
                .userDetailPhone(resultSet.getString("user_phone"))
                .userDetailEmail(resultSet.getString("user_email"))
                .build();
    }
}
