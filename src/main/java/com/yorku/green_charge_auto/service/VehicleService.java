package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.dto.VehicleRequest;
import com.yorku.green_charge_auto.dto.VehicleResponse;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.model.VehicleColors;
import com.yorku.green_charge_auto.repository.VehicleColorRepository;
import com.yorku.green_charge_auto.repository.VehicleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleColorRepository colorRepository;

    public List<VehicleResponse> getAllVehicles() {

        return vehicleRepository.findAll().stream().map(this::toVehicleResponse).toList();
    }

    public Optional<Vehicle> getVehicleById(int id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrand(brand);
    }

    public Vehicle addVehicle(VehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setDescription(request.getDescription());
        vehicle.setMileage(request.getMileage());
        vehicle.setManufacturedYear(request.getManufacturedYear());
        vehicle.setHasBeenInAccident(request.isHasBeenInAccident());
        vehicle.setBody(request.getBody());
        vehicle.setPrice(request.getPrice());
        vehicle.setQuantity(request.getQuantity());
        vehicle.setImage(request.getImage());
        vehicle.setHotDeal(request.isHotDeal());

        Set<VehicleColors> validColors = request.getColors().stream()
                .map(colors -> colorRepository.findById(colors))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        vehicle.setAvailableColors(validColors);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(int id, VehicleRequest request) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setBrand(request.getBrand());
            vehicle.setModel(request.getModel());
            vehicle.setDescription(request.getDescription());
            vehicle.setMileage(request.getMileage());
            vehicle.setManufacturedYear(request.getManufacturedYear());
            vehicle.setHasBeenInAccident(request.isHasBeenInAccident());
            vehicle.setBody(request.getBody());
            vehicle.setPrice(request.getPrice());
            vehicle.setQuantity(request.getQuantity());
            vehicle.setImage(request.getImage());
            vehicle.setHotDeal(request.isHotDeal());

            Set<VehicleColors> colors = request.getColors().stream()
                    .map(color -> colorRepository.findById(color))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());

            vehicle.setAvailableColors(colors);

            return vehicleRepository.save(vehicle);
        }).orElseThrow(() -> new RuntimeException("Vehicle not found with id " + id));
    }


    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(id);
    }

    public VehicleResponse toVehicleResponse(Vehicle vehicle) {
        List<String> colorNames = vehicle.getAvailableColors().stream()
                .map(vc -> vc.getColors().name())
                .toList();

        return new VehicleResponse(
                vehicle.getVid(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getDescription(),
                vehicle.getMileage(),
                vehicle.getManufacturedYear(),
                vehicle.isHasBeenInAccident(),
                vehicle.getBody(),
                vehicle.getPrice(),
                vehicle.getQuantity(),
                vehicle.getImage(),
                vehicle.isHotDeal(),
                colorNames
        );
    }

}
