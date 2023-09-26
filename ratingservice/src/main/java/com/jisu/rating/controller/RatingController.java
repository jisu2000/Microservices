package com.jisu.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jisu.rating.model.Rating;
import com.jisu.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	private RatingService service;
	
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(rating));
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings (){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAll());
	}
	
	@GetMapping("/users/{uid}")
	public ResponseEntity<List<Rating>> getRatingsByuid ( @PathVariable String uid){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getRatingByUserId(uid));
	}
	
	
	@GetMapping("/hotels/{hid}")
	public ResponseEntity<List<Rating>> getRatingByhid(@PathVariable String hid){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getRatingByHotelId(hid));
	}
	
	
}
