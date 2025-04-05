package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.dto.VehicleRequest;
import com.yorku.green_charge_auto.dto.VehicleResponse;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class CatalogController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<VehicleResponse> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable String id) {
        int vid = Integer.parseInt(id);
        return vehicleService.getVehicleById(vid)
                .map(vehicleService::toVehicleResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/brand/{brand}")
    public List<VehicleResponse> getVehiclesByBrand(@PathVariable String brand) {
        return vehicleService.getVehiclesByBrand(brand).stream()
                .map(vehicleService::toVehicleResponse)
                .toList();
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody VehicleRequest vehicle) {
        Vehicle saveVehicle = vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(vehicleService.toVehicleResponse(saveVehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable int id, @RequestBody VehicleRequest updatedVehicle) {
        try {
            Vehicle updated = vehicleService.updateVehicle(id, updatedVehicle);
            return ResponseEntity.ok(vehicleService.toVehicleResponse(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
