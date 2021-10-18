package com.hola.holalandtraffic.mapper;

import com.hola.holalandtraffic.entity.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet resultSet, int i) throws SQLException {
        return Member.builder()
                
                .build();
    }
}
