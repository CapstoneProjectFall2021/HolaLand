package com.hola.holalandcore.service;

import com.hola.holalandcore.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByUserEmail(String email);
}
