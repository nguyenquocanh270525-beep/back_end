package com.example.rewebdemo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBookingRequest {

    private Long guestId;
    private Long roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;


}
