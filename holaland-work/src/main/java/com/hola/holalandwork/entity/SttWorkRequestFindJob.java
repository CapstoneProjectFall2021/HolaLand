package com.hola.holalandwork.entity;

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
public class SttWorkRequestFindJob {

    private int sttWorkRequestFindJobId;
    private String sttWorkRequestFindJobStatus;
}
