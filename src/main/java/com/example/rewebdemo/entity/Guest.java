package com.example.rewebdemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "guests")
@Data
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long guestId;

    @Column (name = "name")
    private String guestName;

    @Column(name = "gender")
    private String gender;
}
