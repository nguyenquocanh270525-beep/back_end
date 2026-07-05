    package com.example.rewebdemo.dto;

    import lombok.Data;

    import java.time.LocalDate;

    @Data
    public class UpdateBookingRequest {
        private LocalDate checkIn;
        private LocalDate checkOut;


    }
