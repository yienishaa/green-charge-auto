package com.yorku.green_charge_auto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    @JsonProperty("review_id")
    private int id;

    @JsonProperty("star_rating")
    private int stars;

    @JsonProperty("review_content")
    private String content;

    @JsonProperty("author")
    private String author;


}
