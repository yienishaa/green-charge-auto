package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.dto.ReviewResponse;
import com.yorku.green_charge_auto.dto.VehicleResponse;
import com.yorku.green_charge_auto.model.Reviews;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.VehicleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
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
    	vehicle.setBody(updatedVehicle.getBody());
    	vehicle.setBrand(updatedVehicle.getBrand());
    	vehicle.setColors(updatedVehicle.getColors());
    	vehicle.setDescription(updatedVehicle.getDescription());
    	vehicle.setHasBeenInAccident(updatedVehicle.isHasBeenInAccident());
    	vehicle.setImage(updatedVehicle.getImage());
    	vehicle.setManufacturedYear(updatedVehicle.getManufacturedYear());
    	vehicle.setModel(updatedVehicle.getModel());
        vehicle.setPrice(updatedVehicle.getPrice());
        vehicle.setQuantity(updatedVehicle.getQuantity());
        vehicle.setReviews(updatedVehicle.getReviews());
        vehicle.setHotDeal(updatedVehicle.isHotDeal());
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
    vehicleResponse.setBody(vehicle.getBody());
    vehicleResponse.setBrand(vehicle.getBrand());
    vehicleResponse.setColors(vehicle.getColors());
    vehicleResponse.setDescription(vehicle.getDescription());
    vehicleResponse.setHasBeenInAccident(vehicle.isHasBeenInAccident());
    vehicleResponse.setImage(vehicle.getImage());
    vehicleResponse.setManufacturedYear(vehicle.getManufacturedYear());
    vehicleResponse.setMileage(vehicle.getMileage());
    vehicleResponse.setModel(vehicle.getModel());
    vehicleResponse.setPrice(vehicle.getPrice());
    vehicleResponse.setQuantity(vehicle.getQuantity());
    vehicleResponse.setReviews(reviewResponseList);
    vehicleResponse.setHotDeal(vehicle.isHotDeal());
    vehicleResponse.setVid(vehicle.getVid());


    return vehicleResponse;
}

}
