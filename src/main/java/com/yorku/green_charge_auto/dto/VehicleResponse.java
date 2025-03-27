package com.yorku.green_charge_auto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//This class is used so that we don't expose our database structure outside

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {

    private Integer vid;

    private String brand;

    private String model;

    private String manufacturedYear;

    private double price;

    private double mileage;

    private List<String> colors;

    private List<ReviewResponse> reviews;
}
