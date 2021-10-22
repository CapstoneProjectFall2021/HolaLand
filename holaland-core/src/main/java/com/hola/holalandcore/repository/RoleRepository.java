package com.hola.holalandcore.repository;

import com.hola.holalandcore.entity.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> getRolesByUserEmail(String email);
}
