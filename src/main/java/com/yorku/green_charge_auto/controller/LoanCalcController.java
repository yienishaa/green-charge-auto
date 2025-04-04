package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.constants.province;
import com.yorku.green_charge_auto.dto.LoanCalculatorResponse;
import com.yorku.green_charge_auto.service.LoanCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan-calculator")
public class LoanCalcController {

    @Autowired
    private LoanCalcService loanCalcService;

    @GetMapping
    public LoanCalculatorResponse loanCalc(
            @RequestParam double priceOfVehicle,
            @RequestParam double downPayment,
            @RequestParam int loanDuration,
            @RequestParam double interestRate,
            @RequestParam province state) {

        return loanCalcService.calculateMonthlyLoan(priceOfVehicle, downPayment, loanDuration, interestRate, state);
    }

}
