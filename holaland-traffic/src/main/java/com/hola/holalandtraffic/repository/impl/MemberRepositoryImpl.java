package com.hola.holalandtraffic.repository.impl;

import com.hola.holalandtraffic.entity.Member;
import com.hola.holalandtraffic.mapper.MemberMapper;
import com.hola.holalandtraffic.repository.IRepositoryQuery;
import com.hola.holalandtraffic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository, IRepositoryQuery {
}