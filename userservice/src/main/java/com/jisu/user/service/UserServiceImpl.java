package com.jisu.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jisu.user.Model.Rating;
import com.jisu.user.Model.User;
import com.jisu.user.dao.UserDao;
import com.jisu.user.exception.ResourceNotFoundException;
import com.jisu.user.external.services.HotelService;
import com.jisu.user.external.services.RatingService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	// @Autowired
	// private RestTemplate restTemplate;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private HotelService hotelService;

	@Override
	public User saveUser(User u) {

		String uid = UUID.randomUUID().toString();
		u.setUserId(uid);
		return this.dao.save(u);
	}

	@Override
	public List<User> getAll() {
		List<User> list = this.dao.findAll();

		for (User u : list) {
			// ResponseEntity<Rating[]> responseEntity = restTemplate.getForEntity(
			// "http://RATING-SERVICE/ratings/users/" + u.getUserId(), Rating[].class);

			Rating[] ratings = ratingService.getRatings(u.getUserId());

			// for (Rating r : ratings) {

			// // (Hotel) restTemplate.getForObject(

			// // "http://HOTEL-SERVICE/hotels/" + r.getHotelId(), Hotel.class);

			// }

			List<Rating> ratingsList = Arrays.asList(ratings);
			for (Rating r : ratingsList) {
				String hotelname = this.hotelService.getHotelname(r.getHotelId());
				r.setHotelName(hotelname);
			}

			u.setRatings(ratingsList);

		}

		return list;
	}

	@Override
	public User getUser(String id) {
		User user = this.dao.findById(id).orElseThrow(() -> new ResourceNotFoundException());

		// Get ratings from Rating API
		// ResponseEntity<Rating[]> responseEntity = restTemplate.getForEntity(
		// "http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

		Rating[] ratings = ratingService.getRatings(user.getUserId());

		List<Rating> ratingList = Arrays.asList(ratings);

		for (Rating r : ratingList) {
			String hotelname = this.hotelService.getHotelname(r.getHotelId());
			r.setHotelName(hotelname);
		}

		user.setRatings(ratingList);

		return user;
	}

	@Override
	public User delUser(String id) {

		User temp = this.getUser(id);

		if (temp == null) {
			return null;
		}
		this.dao.deleteById(id);
		return temp;
	}

	@Override
	public User updateUser(User u, String id) {
		u.setUserId(id);
		return this.dao.save(u);
	}

	@Override
	public ArrayList<User> findbyname(String name) {

		ArrayList<User> list = this.dao.findByName(name);

		if (!list.isEmpty()) {

			for (User u : list) {
				Rating[] ratings = ratingService.getRatings(u.getUserId());
				List<Rating> ratingList = Arrays.asList(ratings);

				u.setRatings(ratingList);

			}
			return list;
		}

		else {
			throw new ResourceNotFoundException("CHECK NAME SPELLING!!");
		}
	}

	@Override
	public String getName(String id) {

		User user = this.dao.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return user.getName();
	}

}
