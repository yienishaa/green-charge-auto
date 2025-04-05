package com.yorku.green_charge_auto.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private int cartItemId;
    private int vid; 
    private String vehicleName;
    private String imageUrl;
    private double price;
    private int quantity;
}
