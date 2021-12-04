package com.hola.holalandcore.mapper;

import com.hola.holalandcore.entity.UserAddress;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddressMapper implements RowMapper<UserAddress> {

    @Override
    public UserAddress mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserAddress.builder()
                .userAddressId(resultSet.getInt("user_address_id"))
                .userDetailId(resultSet.getInt("user_detail_id"))
                .userName(resultSet.getString("user_name"))
                .userPhone(resultSet.getString("user_phone"))
                .userAddress(resultSet.getString("user_address"))
                .userAddressDefault(resultSet.getBoolean("user_address_default"))
                .userAddressDeleted(resultSet.getBoolean("user_address_deleted"))
                .build();
    }
}
