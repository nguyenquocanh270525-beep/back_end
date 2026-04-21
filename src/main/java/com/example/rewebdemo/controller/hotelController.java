package com.example.rewebdemo.controller;

import com.example.rewebdemo.dto.CreateHotelRequest;
import com.example.rewebdemo.dto.UpdateHotelRequest;
import com.example.rewebdemo.entity.Hotel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class hotelController {
    public static List<Hotel> hotels = new ArrayList<Hotel>();

    @PostMapping("/hotels")
    public Hotel creareHotel(@RequestBody CreateHotelRequest request) {
            Hotel hotel = new Hotel();

            hotel.setHotelId(request.getHotelId());
            hotel.setHotelName(request.getHotelName());
            hotels.add(hotel);
            return hotel;
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotels(){
        return hotels;
    }

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId){
        return findHotelById(hotelId);
    }

    @PutMapping("/hotels/{hotelId}")
    public Hotel updateHotel(@PathVariable String hotelId,
                             @RequestBody UpdateHotelRequest request){
        Hotel hotel = findHotelById(hotelId);
        if(hotel == null){
            return null;
        }
        hotel.setHotelName(request.getHotelName());
        hotel.setStatus(request.isStatus());

        return hotel;


    }
    private Hotel findHotelById(String hotelId){
        for (Hotel hotel : hotels){
            if(hotel.getHotelId().equals(hotelId)){
                return hotel;
            }

        }
        return null;
    }




}
