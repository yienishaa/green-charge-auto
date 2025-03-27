package com.yorku.green_charge_auto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {
    private Integer vid;
    private String name;
    private String brand;
    private String model;
    private Integer manufacturedYear;
    private List<ReviewResponse> reviews;
}
