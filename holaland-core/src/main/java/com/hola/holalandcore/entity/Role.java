package com.hola.holalandcore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Role implements Serializable {

    private int roleId;
    private String roleName;
}
