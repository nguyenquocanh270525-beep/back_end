package com.example.rewebdemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "hotel")
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long hotelId;

    @Column(name = "name")
    private String hotelName;


    @Column(name = "status")
    private boolean status;

    @Transient
    private int rate;

}
