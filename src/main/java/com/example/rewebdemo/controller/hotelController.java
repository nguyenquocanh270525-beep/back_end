package com.example.rewebdemo.controller;

import com.example.rewebdemo.dto.CreateHotelRequest;
import com.example.rewebdemo.dto.UpdateHotelRequest;
import com.example.rewebdemo.entity.Hotel;
import com.example.rewebdemo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels/")
public class hotelController {
    public static List<Hotel> hotels = new ArrayList<Hotel>();
    @Autowired
    HotelService hotelService;


    @PostMapping("")
    public Hotel creareHotel(@RequestBody CreateHotelRequest request) {
            return hotelService.createHotel(request);
    }

    @GetMapping("")
    public List<Hotel> getHotels(){
        return hotelService.getAll();
    }



    @GetMapping("{hotelId}")
    public Hotel getHotel(@PathVariable Long hotelId){
        return hotelService.getHotelById(hotelId);
    }

    @PutMapping("{hotelId}")
    public Hotel updateHotel(@PathVariable Long hotelId,
                             @RequestBody UpdateHotelRequest request) {
        return hotelService.updateHotel(hotelId,request);
    }
    @DeleteMapping("{hotelId}")
    public Hotel deleteHotel(@PathVariable Long hotelId){
        return hotelService.disableHotel(hotelId);
    }







}
