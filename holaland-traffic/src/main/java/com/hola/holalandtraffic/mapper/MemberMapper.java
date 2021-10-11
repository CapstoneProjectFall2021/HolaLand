package com.hola.holalandtraffic.mapper;

import com.hola.holalandtraffic.entity.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet resultSet, int i) throws SQLException {
        return Member.builder()
                .memberId(resultSet.getInt("member_id"))
                .memberName(resultSet.getString("member_name"))
                .memberGender(resultSet.getBoolean("member_gender"))
                .memberDob(resultSet.getDate("member_dob"))
                .memberMobile(resultSet.getString("member_mobile"))
                .memberEmail(resultSet.getString("member_email"))
                .memberRankId(resultSet.getInt("member_rank_id"))
                .memberStatusId(resultSet.getInt("member_status_id"))
                .memberDelete(resultSet.getBoolean("member_delete"))
                .build();
    }
}
