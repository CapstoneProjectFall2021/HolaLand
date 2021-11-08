package com.hola.holalandcore.entity;

import lombok.*;

@Data
@Builder
public class UserDetail {
    private int userDetailId;
    private int userId;
    private String userDetailName;
    private String userDetailDob;
    private int userDetailGender;
    private String userDetailPhone;
    private String userDetailEmail;
}