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
public class UserAddress {

    private int userAddressId;
    private int userId;
    private String userName;
    private String userPhone;
    private String userAddress;
    private Boolean userAddressDefault;
    private Boolean userAddressDeleted;
}
