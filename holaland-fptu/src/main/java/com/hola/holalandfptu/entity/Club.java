package com.hola.holalandfptu.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Club {

    private int fptuClubId;
    private String fptuClubName;
    private String fptuClubDescription;
    private String fptuClubLogo;
    private int fptuClubType;
    private String fptuClubFanpage;
    private int fptuClubMemberQuantity;
    private String fptuClubContactName;
    private String fptuClubContactEmail;
    private String fptuClubContactPhone;
    private String fptuClubAchievements;
    private boolean fptuClubDeleted;
}
