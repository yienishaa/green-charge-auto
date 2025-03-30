package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.model.Reviews;
import com.yorku.green_charge_auto.repository.ReviewRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Reviews> getReviews(int vid) {
        return reviewRepository.findByVehicleVid(vid);
    }

    public ResponseEntity<String> saveReviews(Reviews reviews) {
        reviewRepository.save(reviews);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
