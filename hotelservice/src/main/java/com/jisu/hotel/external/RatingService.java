package com.jisu.hotel.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jisu.hotel.model.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("ratings/hotels/{hotelId}")
    Rating[] getAllRatings(@PathVariable String hotelId);

}
