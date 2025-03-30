package com.yorku.green_charge_auto.dto;

import lombok.*;

import java.util.List;

//This class is used so that we don't expose our database structure outside

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {
    private Integer vid;

    private String brand;
    private String model;

    private Integer manufacturedYear;
    private double mileage;
    private String body;
    private String description;
    private boolean hasBeenInAccident;
    private Integer quantity;
    private double price;
    private List<String> colors;
    private List<ReviewResponse> reviews;
    private String image;
    private boolean hotDeal;
}
