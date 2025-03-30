package com.yorku.green_charge_auto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanCalculatorResponse {

    @JsonProperty("orig_vehicle_price")
    private double loanAmount;

    @JsonProperty("monthly_interest_rate")
    private double monthlyRate;

    @JsonProperty("sales_tax")
    double salesTax;

    @JsonProperty("vehicle_price_with_tax")
    double priceOfCarAfterTax;

    @JsonProperty("vehicle_price_after_downpayment")
    double priceAfterDownPayment;

    @JsonProperty("monthly_loan_payment")
    double payment;
}
