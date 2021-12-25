package com.hola.holalandcore.service.impl;

import com.hola.holalandcore.entity.Role;
import com.hola.holalandcore.repository.RoleRepository;
import com.hola.holalandcore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRolesByUserEmail(String email) {
        return roleRepository.getRolesByUserEmail(email);
    }
}
