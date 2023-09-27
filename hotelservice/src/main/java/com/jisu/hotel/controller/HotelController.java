package com.jisu.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jisu.hotel.model.Hotel;
import com.jisu.hotel.service.HotelImpl;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelImpl service;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getById(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Hotel> delhotel(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.deleteHotel(hotelId));
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId,
            @RequestBody Hotel hotel) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.updateHotel(hotelId, hotel));
    }

    @GetMapping("/hotelname/{id}")
    public ResponseEntity<String> getHotelName(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.getHotelname(id));

    }

}
