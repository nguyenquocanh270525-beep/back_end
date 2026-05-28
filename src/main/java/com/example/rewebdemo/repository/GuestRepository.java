package com.example.rewebdemo.repository;

import com.example.rewebdemo.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Guest findByGuestId(Long guestId);


}


