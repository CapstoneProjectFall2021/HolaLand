package com.hola.holalandcore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDetail {

    private int userDetailId;
    private int userId;
    private String userDetailName;
    private String userDetailDob;
    private int userDetailGender;
    private String userDetailPhone;
    private String userDetailEmail;
}