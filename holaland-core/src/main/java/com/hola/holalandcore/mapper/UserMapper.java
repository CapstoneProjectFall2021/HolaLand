package com.hola.holalandcore.mapper;

import com.hola.holalandcore.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return User.builder()
                .userId(resultSet.getInt("user_id"))
                .userEmail(resultSet.getString("user_email"))
                .userPassword(resultSet.getString("user_password"))
                .userCreateTime(resultSet.getLong("user_create_time"))
                .userStatusId(resultSet.getInt("user_status_id"))
                .userDeleted(resultSet.getBoolean("user_deleted"))
                .build();
    }
}
