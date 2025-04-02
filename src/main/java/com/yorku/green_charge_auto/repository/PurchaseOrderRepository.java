package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

//    @Query("SELECT SUM(v.totalPrice) FROM PurchaseOrder v")
//    Double getTotalPrice();
//
//    //Monthly order count (sales)
//    @Query("SELECT new com.yorku.green_charge_auto.dto.MonthlySalesResponse(" +
//            "FUNCTION('MONTHNAME', po.createdAt), COUNT(po)) " +
//            "FROM PurchaseOrder po " +
//            "GROUP BY FUNCTION('MONTH', po.createdAt), FUNCTION('MONTHNAME', po.createdAt)")
//    List<MonthlySalesResponse> getMonthlySales();
//
//    //Vehicle type count from sold vehicles
//    @Query("SELECT new com.yorku.green_charge_auto.dto.VehicleTypeSalesResponse(" +
//            "v.model, COUNT(v)) " +
//            "FROM PurchaseOrderVehicle pov " +
//            "JOIN pov.vehicle v " +
//            "JOIN pov.purchaseOrder po " +
//            "GROUP BY v.model")
//    List<VehicleTypeSalesResponse> getSalesByVehicleType();
//
//    //Total profit per month (from totalPrice)
//    @Query("SELECT new com.yorku.green_charge_auto.dto.ProfitResponse(" +
//            "FUNCTION('MONTHNAME', po.createdAt), SUM(po.totalPrice)) " +
//            "FROM PurchaseOrder po " +
//            "GROUP BY FUNCTION('MONTH', po.createdAt), FUNCTION('MONTHNAME', po.createdAt)")
//    List<ProfitResponse> getMonthlyProfit();
}
