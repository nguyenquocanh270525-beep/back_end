package com.example.rewebdemo.entity;

import lombok.Data;

@Data
public class Hotel {
    private String hotelId;
    private String hotelName;
    private boolean status = true;

}
