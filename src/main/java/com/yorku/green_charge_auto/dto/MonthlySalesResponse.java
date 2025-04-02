package com.yorku.green_charge_auto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySalesResponse {

    private Integer year;
    private String month;
    private Double sales;

}

