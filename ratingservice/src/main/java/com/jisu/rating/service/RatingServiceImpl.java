package com.jisu.rating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jisu.rating.model.Rating;
import com.jisu.rating.repo.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepo repo;

	@Override
	public Rating create(Rating r) {
		// TODO Auto-generated method stub
		return this.repo.save(r);
	}

	@Override
	public List<Rating> getAll() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return this.repo.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return this.repo.findByHotelId(hotelId);
	}

}
