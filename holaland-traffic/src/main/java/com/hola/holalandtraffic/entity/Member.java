package com.hola.holalandtraffic.entity;

import lombok.*;

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
