package com.yorku.green_charge_auto.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartRequest {
    private Integer cartItemId;
    private Integer quantity;
}
