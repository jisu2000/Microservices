package com.jisu.user.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jisu.user.Model.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    
    @GetMapping("ratings/users/{userId}")
    Rating[] getRatings(@PathVariable String userId);


    @PostMapping("/rating")
    public Rating createRating(Rating values);

    @PutMapping("/ratings/{ratingId}")
    public Rating  updateRating(@PathVariable String ratingId,Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    public void deleteMapping(@PathVariable String ratingId);

}
