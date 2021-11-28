package com.hola.holalandcore.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserAddress {

    private int userAddressId;
    private int userDetailId;
    private String userName;
    private String userPhone;
    private String userAddress;
}
