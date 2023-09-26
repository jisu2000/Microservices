package com.jisu.hotel.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jisu.hotel.model.Hotel;

public interface HotelRepo extends MongoRepository<Hotel, String>{

}
