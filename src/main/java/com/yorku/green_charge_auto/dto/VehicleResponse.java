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

    public VehicleResponse(
            Integer vid,
            String brand,
            String model,
            String description,
            double mileage,
            Integer manufacturedYear,
            boolean hasBeenInAccident,
            String body,
            Double price,
            Integer quantity,
            String image,
            boolean hotDeal,
            List<String> colorNames) {
        this.vid = vid;
        this.brand = brand;
        this.model = model;
        this.manufacturedYear = manufacturedYear;
        this.hasBeenInAccident = hasBeenInAccident;
        this.body = body;
        this.description = description;
        this.mileage = mileage;
        this.quantity = quantity;
        this.price = price;
        this.colors = colorNames;
        this.image = image;
        this.hotDeal = hotDeal;
    }
}
