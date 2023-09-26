package com.jisu.rating.service;

import java.util.List;

import com.jisu.rating.model.Rating;

public interface RatingService {
	
	Rating create(Rating r);
	
	List<Rating>getAll();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
}
