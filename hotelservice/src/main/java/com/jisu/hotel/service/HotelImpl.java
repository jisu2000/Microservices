package com.jisu.hotel.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jisu.hotel.exception.ResourceNotFoundException;
import com.jisu.hotel.external.RatingService;
import com.jisu.hotel.external.UserService;
import com.jisu.hotel.model.Hotel;
import com.jisu.hotel.model.Rating;
import com.jisu.hotel.model.User;
import com.jisu.hotel.repo.HotelRepo;

@Service
public class HotelImpl implements HotelService {

	@Autowired
	private HotelRepo repo;

	@Autowired
	private RatingService service;

	@Autowired
	private UserService userService;

	@Override
	public Hotel create(Hotel hotel) {
		// TODO Auto-generated method stub
		String hid = UUID.randomUUID().toString();
		hotel.setId(hid);
		return this.repo.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub

		List<Hotel> list = this.repo.findAll();
		for (Hotel h : list) {
			Rating[] ratings = this.service.getAllRatings(h.getId());
			List<Rating> ratingList = Arrays.asList(ratings);
			for (Rating r : ratingList) {
				String username = this.userService.getUser(r.getUserId());
				r.setUsername(username);

			}
			h.setRatings(ratingList);
		}
		return list;
	}

	@Override
	public Hotel getById(String id) {
		// TODO Auto-generated method stub
		Hotel hotel = (Hotel) this.repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("hotel not found"));

		Rating[] allratings = service.getAllRatings(id);
		List<Rating> ratings = Arrays.asList(allratings);
		for (Rating r : ratings) {
			String username = this.userService.getUser(r.getUserId());
			r.setUsername(username);
		}
		hotel.setRatings(ratings);
		return hotel;
	}

	@Override
	public Hotel deleteHotel(String id) {
		Hotel hotel = getById(id);
		if (hotel != null) {
			this.repo.delete(hotel);
			return hotel;
		} else {
			throw new ResourceNotFoundException("Hotel not found");
		}
	}

	@Override
	public Hotel updateHotel(String id, Hotel hotel) throws ResourceNotFoundException {

		Hotel temp = getById(id);
		if (temp != null) {

			Hotel saved = new Hotel(id, hotel.getName(), hotel.getLocation(), hotel.getAbout());

			repo.delete(temp);

			repo.save(saved);

			return saved;
		} else {
			throw new ResourceNotFoundException("Hotel not found");
		}

	}

}
