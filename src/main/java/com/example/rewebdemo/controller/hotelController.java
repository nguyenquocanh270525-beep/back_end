package com.example.rewebdemo.controller;

import com.example.rewebdemo.dto.CreateHotelRequest;
import com.example.rewebdemo.dto.ResponseDto;
import com.example.rewebdemo.dto.UpdateHotelRequest;
import com.example.rewebdemo.entity.Hotel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class hotelController {
    public static List<Hotel> hotels = new ArrayList<Hotel>();

    @PostMapping("/")
    public Hotel creareHotel(@RequestBody CreateHotelRequest request) {
            Hotel hotel = new Hotel();
            hotel.setHotelId(request.getHotelId());
            hotel.setHotelName(request.getHotelName());
            hotel.setRate(request.getRate());
            hotels.add(hotel);
            return hotel;
    }

    @GetMapping("/")
    public List<Hotel> getHotels(@RequestParam(required = false) Integer rate){
        if(rate != null){
            List<Hotel> result = new LinkedList<>();
            for(Hotel hotel : hotels ){
                if(hotel.getRate() == rate){
                    result.add(hotel);
                }
            }

            return result;
        }
        return hotels;
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId){
        return findHotelById(hotelId);
    }

    @PutMapping("/{hotelId}")
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

    @DeleteMapping("/{hotelId}")
    public ResponseDto disableHotel(@PathVariable String hotelId){
        for(int i = 0; i<hotels.size(); i++){
            if(hotels.get(i).getHotelId().equals(hotelId)){
                hotels.get(i).setStatus(false);
                return new ResponseDto(true, "successful");
            }

        }
        return new ResponseDto(false, "not found");

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
