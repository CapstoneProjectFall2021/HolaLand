package com.hola.holalandwork.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class SttWork {

    private int sttWorkId;
    private String sttWorkName;
    private int sttWorkCode;
    private String sttWorkValue;
    private String sttWorkIcon;
}
