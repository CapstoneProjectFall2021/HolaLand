package com.hola.holalandcore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User implements Serializable {

    private int userId;
    private String userEmail;
    private String userPassword;
    private long userCreateTime;
    private int userStatusId;
    private boolean userDeleted;
}
