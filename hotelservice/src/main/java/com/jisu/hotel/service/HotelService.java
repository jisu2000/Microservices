package com.jisu.hotel.service;

import java.util.List;

import com.jisu.hotel.model.Hotel;

public interface HotelService {

	public Hotel create(Hotel hotel);

	public List<Hotel> getAll();

	public Hotel getById(String id);

	public Hotel deleteHotel(String id);

	public Hotel updateHotel(String id, Hotel hotel);

	public String getHotelname(String id);
}
