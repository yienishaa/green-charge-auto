package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.dto.ReviewResponse;
import com.yorku.green_charge_auto.model.Reviews;
import com.yorku.green_charge_auto.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/vehicle/{id}/read-review")
    public List<Reviews> getReviews(@PathVariable int id) {
        return reviewService.getReviews(id);
    }

    @PostMapping("/save-review")
    public void saveReview(@RequestBody Reviews review) {
        reviewService.saveReviews(review);
    }
}
