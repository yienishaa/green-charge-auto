package com.yorku.green_charge_auto.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private int id; //cart item id
    private String vehicleName;
    private String imageUrl;
    private double price;
    private int quantity;
}
