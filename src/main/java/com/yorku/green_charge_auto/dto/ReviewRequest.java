package com.yorku.green_charge_auto.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private int vid;
    private int stars;
    private String content;
    private String author;
}
