package com.yorku.green_charge_auto.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {

    private Integer vid;
    private Integer userId;
    private Integer quantity;

}
