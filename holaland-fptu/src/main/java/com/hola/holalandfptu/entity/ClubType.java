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
public class ClubType {

    private int fptuClubTypeId;
    private String fptuClubTypeIcon;
    private String fptuClubTypeName;
    private int fptuClubTypeCount;
    private boolean fptuClubTypeDeleted;
}
