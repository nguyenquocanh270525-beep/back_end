package com.example.rewebdemo.dto;

import lombok.Data;

@Data
public class UpdateHotelRequest {
    private String hotelName;
    private boolean status;
}
