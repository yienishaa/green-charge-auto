package com.yorku.green_charge_auto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartRequest {
    private Integer cartItemId;
    private Integer quantity;
}
