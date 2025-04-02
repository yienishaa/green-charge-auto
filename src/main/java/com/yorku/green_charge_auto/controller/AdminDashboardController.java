//package com.yorku.green_charge_auto.controller;
//
//import com.yorku.green_charge_auto.dto.MonthlySalesResponse;
//import com.yorku.green_charge_auto.dto.ProfitResponse;
//import com.yorku.green_charge_auto.dto.VehicleTypeSalesResponse;
//import com.yorku.green_charge_auto.service.SalesAnalyticsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/dashboard")
//public class AdminDashboardController {
//
//    @Autowired
//    private SalesAnalyticsService salesAnalyticsService;
//
//    @GetMapping("/monthly")
//    public List<MonthlySalesResponse> getMonthlySales() {
//        return salesAnalyticsService.getMonthlySales();
//    }
//
//    @GetMapping("/vehicle-types")
//    public List<VehicleTypeSalesResponse> getVehicleTypeSales() {
//        return salesAnalyticsService.getVehicleTypeSales();
//    }
//
//    @GetMapping("/profit")
//    public List<ProfitResponse> getProfitTrend() {
//        return salesAnalyticsService.getProfitTrend();
//    }
//}
