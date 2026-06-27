package com.example.rewebdemo.dto;

import lombok.Data;

@Data
public class CreateRoomRequest {

    private String roomNumber;
    private String roomType;
    private Double price;
    private Integer floor;
    private Integer capacity;

}
