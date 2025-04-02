package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.dto.MonthlySalesResponse;
import com.yorku.green_charge_auto.dto.ProfitResponse;
import com.yorku.green_charge_auto.dto.VehicleTypeSalesResponse;
import com.yorku.green_charge_auto.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

    @Query("SELECT SUM(v.totalPrice) FROM PurchaseOrder v")
    Double getTotalPrice();

    //Monthly order count (sales)
    @Query(value = "SELECT YEAR(created_at) AS year, " +
            "MONTHNAME(created_at) AS month, " +
            "SUM(total_price) AS sales " +
            "FROM purchase_order " +
            "GROUP BY YEAR(created_at), MONTH(created_at), MONTHNAME(created_at) " +
            "ORDER BY YEAR(created_at), MONTH(created_at)",
            nativeQuery = true)
    List<MonthlySalesResponse> getMonthlySales();

    //Vehicle type count from sold vehicles
    @Query(
            value = "SELECT v.brand AS type, COUNT(*) AS count FROM purchase_order_vehicle as pov \n" +
                    "JOIN vehicle v ON pov.vid = v.vid JOIN purchase_order po ON pov.order_id = po.order_id GROUP BY v.brand;",
            nativeQuery = true
    )
    List<VehicleTypeSalesResponse> getSalesByVehicleType();

    //Total profit per month (from totalPrice)
    @Query(
            value = "SELECT YEAR(created_at) AS year, " +
                    "MONTHNAME(created_at) AS month, " +
                    "SUM(total_price) AS profit " +
                    "FROM purchase_order " +
                    "GROUP BY YEAR(created_at), MONTH(created_at), MONTHNAME(created_at) " +
                    "ORDER BY YEAR(created_at), MONTH(created_at)",
            nativeQuery = true
    )
    List<ProfitResponse> getMonthlyProfit();
}
