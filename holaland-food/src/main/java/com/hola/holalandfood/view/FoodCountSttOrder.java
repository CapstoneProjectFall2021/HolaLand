package com.hola.holalandfood.view;

import lombok.*;

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
