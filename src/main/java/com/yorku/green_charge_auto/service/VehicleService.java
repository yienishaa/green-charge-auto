package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.dto.ReviewResponse;
import com.yorku.green_charge_auto.dto.VehicleResponse;
import com.yorku.green_charge_auto.model.Reviews;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleResponse> getAllVehicles() {
        List<Vehicle> vehiclesList = vehicleRepository.findAll();
        List<VehicleResponse> vehicleResponseList = new ArrayList<>();
        for (Vehicle vehicle : vehiclesList) {
            vehicleResponseList.add(toVehicleResponse(vehicle));
        }
        return vehicleResponseList;
    }

    public List<Vehicle> getAdminAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(int id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrand(brand);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(int id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setDescription(updatedVehicle.getDescription());
            vehicle.setBrand(updatedVehicle.getBrand());
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setPrice(updatedVehicle.getPrice());
            vehicle.setQuantity(updatedVehicle.getQuantity());
            vehicle.setManufacturedYear(updatedVehicle.getManufacturedYear());
            return vehicleRepository.save(vehicle);
        }).orElseThrow(() -> new RuntimeException("Vehicle not found with id " + id));
    }


    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(id);
    }

    public VehicleResponse toVehicleResponse(Vehicle vehicle) {
    List<ReviewResponse> reviewResponseList = new ArrayList<>();

    for (Reviews review : vehicle.getReviews()) {
        reviewResponseList.add(new ReviewResponse(
            review.getReviewId(),
            review.getStars(),
            review.getContent(),
            review.getAuthor()
        ));
    }

       VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setVid(vehicle.getVid());
        vehicleResponse.setBrand(vehicle.getBrand());
        vehicleResponse.setModel(vehicle.getModel());
        vehicleResponse.setManufacturedYear(vehicle.getManufacturedYear());
        vehicleResponse.setPrice(vehicle.getPrice());
        vehicleResponse.setMileage(vehicle.getMileage());
        vehicleResponse.setColors(vehicle.getColors());
        vehicleResponse.setReviews(reviewResponseList);

    return vehicleResponse;
}

}
