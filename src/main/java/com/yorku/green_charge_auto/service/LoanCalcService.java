package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.constants.Constants;
import com.yorku.green_charge_auto.constants.province;
import com.yorku.green_charge_auto.dto.LoanCalculatorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class LoanCalcService {

    private double priceOfVehicle;
    private double downPayment;
    private int loanDuration;
    private double interestRate;
    private province state;

    public LoanCalculatorResponse calculateMonthlyLoan(double priceOfVehicle, double downPayment, int loanDuration, double interestRate, province state) {

        double monthlyInterestRate = interestRate / 100 / 12;

        double salesTax = priceOfVehicle * Constants.proviceTaxRate.get(state);
        double priceOfCarAfterTax = salesTax + priceOfVehicle;
        double priceAfterDownPayment = priceOfCarAfterTax - downPayment;

        double numerator = priceAfterDownPayment * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanDuration);
        double denominator = Math.pow(1 + monthlyInterestRate, loanDuration) - 1;

        double monthlyLoan = numerator / denominator;

        monthlyInterestRate = Math.round(monthlyInterestRate * 100.0) / 100.0;
        salesTax = Math.round(salesTax * 100.0) / 100.0;
        priceOfCarAfterTax = Math.round(priceOfCarAfterTax * 100.0) / 100.0;
        priceAfterDownPayment = Math.round(priceAfterDownPayment * 100.0) / 100.0;
        monthlyLoan = Math.round(monthlyLoan * 100.0) / 100.0;

        return new LoanCalculatorResponse(priceOfVehicle, monthlyInterestRate, salesTax, priceOfCarAfterTax, priceAfterDownPayment, monthlyLoan);
    }

}
