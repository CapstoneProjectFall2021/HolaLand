package com.hola.holalandcore.service.impl;

import com.hola.holalandcore.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public String hello() {
        return "HELLO";
    }
}
