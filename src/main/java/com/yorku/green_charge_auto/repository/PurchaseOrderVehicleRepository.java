package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.PurchaseOrderVehicle;
import com.yorku.green_charge_auto.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderVehicleRepository extends JpaRepository<PurchaseOrderVehicle, Integer> {

    @Query("SELECT DISTINCT (v.vehicle) FROM PurchaseOrderVehicle v")
    List<Vehicle> findAllVehicleIds();
}
