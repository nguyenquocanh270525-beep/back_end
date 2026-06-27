package com.example.rewebdemo.controller;

import com.example.rewebdemo.dto.CreateBookingRequest;
import com.example.rewebdemo.entity.Booking;
import com.example.rewebdemo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")

public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody CreateBookingRequest request){

        return bookingService.createBooking(request);
    }

    @GetMapping
    public List<Booking> getAll(){
        return bookingService.getAll();

    }


}
