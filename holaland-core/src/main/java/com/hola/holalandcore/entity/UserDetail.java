package com.hola.holalandcore.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDetail {

    private int userDetailId;
    private int userId;
    private String userDetailName;
    private Date userDetailDob;
    private boolean userDetailGender;
    private String userDetailPhone;
    private String userDetailEmail;
}
