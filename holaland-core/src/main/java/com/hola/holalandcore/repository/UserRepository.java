package com.hola.holalandcore.repository;

import com.hola.holalandcore.entity.User;

public interface UserRepository {

    User findByEmail(String email);
}
