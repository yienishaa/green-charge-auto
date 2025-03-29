package com.yorku.green_charge_auto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
