package com.hola.holalandcore.model;

import com.hola.holalandcore.entity.User;
import com.hola.holalandcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionInfo implements Serializable {

    private User user;

    private final UserRepository userRepository;

    @Autowired
    public UserSessionInfo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        if (user == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = userRepository.findByEmail(authentication.getName());
        }
        return user;
    }
}
