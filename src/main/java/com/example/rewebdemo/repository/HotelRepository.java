package com.example.rewebdemo.repository;

import com.example.rewebdemo.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>  {


    Hotel findByHotelId(Long hotelId);

}
