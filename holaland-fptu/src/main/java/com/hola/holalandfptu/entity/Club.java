package com.hola.holalandfptu.entity;

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
public class Club {

    private int fptuClubId;
    private String fptuClubName;
    private String fptuClubNameEn;
    private String fptuClubDescription;
    private String fptuClubLogo;
    private String fptuClubImage;
    private int fptuClubTypeId;
    private String fptuClubFanpageName;
    private String fptuClubFanpage;
    private int fptuClubMemberQuantity;
    private String fptuClubContactName;
    private String fptuClubContactEmail;
    private String fptuClubContactPhoneNumber;
    private String fptuClubAchievements;
    private String fptuClubStatus;
    private boolean fptuClubDeleted;
}
