package com.example.rewebdemo.repository;

import com.example.rewebdemo.entity.Booking;
import com.example.rewebdemo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoom(Room room);

}
