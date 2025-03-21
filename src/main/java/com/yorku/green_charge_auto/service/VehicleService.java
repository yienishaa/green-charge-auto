package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    private VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(int id) {
        return vehicleRepository.findById(id).get();
    }

    public Vehicle getVehicleByName(String vehicleName) {
        return vehicleRepository.findByName(vehicleName);
    }
}
