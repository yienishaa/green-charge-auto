package com.yorku.green_charge_auto.dto;

import com.yorku.green_charge_auto.constants.Colors;
import lombok.Data;

import java.util.Set;

@Data
public class VehicleRequest {
    private String brand;
    private String model;
    private String description;
    private double mileage;
    private Integer manufacturedYear;
    private boolean hasBeenInAccident;
    private String body;
    private Double price;
    private Integer quantity;
    private String image;
    private boolean hotDeal;
    private Set<Colors> colors;
}
