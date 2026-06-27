package com.example.rewebdemo.dto;

import lombok.Data;

@Data
public class CreateGuestRequest {

    private String guestName;

    private String guestPhone;

    private String guestEmail;

    private String gender;

}
