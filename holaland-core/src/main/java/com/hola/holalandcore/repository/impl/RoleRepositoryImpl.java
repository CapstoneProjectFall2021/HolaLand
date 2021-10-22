package com.hola.holalandcore.repository.impl;

import com.hola.holalandcore.entity.Role;
import com.hola.holalandcore.mapper.RoleMapper;
import com.hola.holalandcore.repository.IRepositoryQuery;
import com.hola.holalandcore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> getRolesByUserEmail(String email) {
        return jdbcTemplate.query(GET_ROLES_BY_USER_EMAIL, new RoleMapper(), email);
    }
}
