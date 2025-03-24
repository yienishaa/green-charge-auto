package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.dto.ReviewResponse;
import com.yorku.green_charge_auto.model.Reviews;
import com.yorku.green_charge_auto.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Reviews> getReviews(int vid) {
        return reviewRepository.findByVehicleVid(vid);
    }

    public void saveReviews(Reviews reviews) {
        reviewRepository.save(reviews);
    }
}
