package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.dto.ReviewRequest;
import com.yorku.green_charge_auto.model.Reviews;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.VehicleRepository;
import com.yorku.green_charge_auto.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/vehicle/{id}/read-review")
    public List<Reviews> getReviews(@PathVariable int id) {
        return reviewService.getReviews(id);
    }

    @PostMapping("/save-review")
    public ResponseEntity<String> saveReview( @RequestBody ReviewRequest request) {

        Vehicle vehicle = vehicleRepository.findById(request.getVid()).get();

        Reviews reviews = new Reviews();
        reviews.setAuthor(request.getAuthor());
        reviews.setContent(request.getContent());
        reviews.setStars(request.getStars());
        reviews.setVehicle(vehicle);

        return reviewService.saveReviews(reviews);
    }
}
