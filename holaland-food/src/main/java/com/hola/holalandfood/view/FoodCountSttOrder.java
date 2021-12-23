package com.hola.holalandfood.view;

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
public class FoodCountSttOrder {
    private int rejectOrder;
    private int completed;
    private int cancel;
}
