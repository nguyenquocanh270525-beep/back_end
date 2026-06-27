    package com.example.rewebdemo.dto;

    import lombok.Data;

    import java.net.Inet4Address;

    @Data
    public class UpdateRoomRequest {

        private String roomType;
        private Integer capacity;
        private Double price;

    }
