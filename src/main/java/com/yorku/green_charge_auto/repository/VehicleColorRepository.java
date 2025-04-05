package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.constants.Colors;
import com.yorku.green_charge_auto.model.VehicleColors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleColorRepository extends JpaRepository<VehicleColors, Colors> {
}
