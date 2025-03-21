package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.PurchaseOrderVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderVehicleRepository extends JpaRepository<PurchaseOrderVehicle, Integer> {
}
