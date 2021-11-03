package com.hola.holalandtraffic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Member {

    private int memberId;
    private String memberName;
    private boolean memberGender;
    private Date memberDob;
    private String memberMobile;
    private String memberEmail;
    private int memberRankId;
    private int memberStatusId;
}
