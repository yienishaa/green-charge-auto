package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.dto.MonthlySalesResponse;
import com.yorku.green_charge_auto.dto.ProfitResponse;
import com.yorku.green_charge_auto.dto.VehicleTypeSalesResponse;
import com.yorku.green_charge_auto.repository.PurchaseOrderRepository;
import com.yorku.green_charge_auto.repository.PurchaseOrderVehicleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class SalesAnalyticsService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderVehicleRepository purchaseOrderVehicleRepository;

    public List<MonthlySalesResponse> getMonthlySales() {
        return purchaseOrderRepository.getMonthlySales();
    }

    public List<VehicleTypeSalesResponse> getVehicleTypeSales() {
        return purchaseOrderRepository.getSalesByVehicleType();
    }

    public List<ProfitResponse> getProfitTrend() {
        return purchaseOrderRepository.getMonthlyProfit();
    }
}
