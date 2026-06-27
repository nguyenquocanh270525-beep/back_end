package com.example.rewebdemo.service;

import com.example.rewebdemo.dto.CreateBookingRequest;
import com.example.rewebdemo.entity.Booking;
import com.example.rewebdemo.entity.Guest;
import com.example.rewebdemo.entity.Room;
import com.example.rewebdemo.repository.BookingRepository;
import com.example.rewebdemo.repository.GuestRepository;
import com.example.rewebdemo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    GuestRepository guestRepository;
    @Autowired
    RoomRepository roomRepository;

    public Booking createBooking(CreateBookingRequest request){



        Guest guest = guestRepository.findById(request.getGuestId())
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

//        if(!"AVAILABLE".equals(room.getStatus()))
//        {
//            throw new RuntimeException("Room is not AVAILABLE");
//        }

        if(request.getCheckIn().isBefore(LocalDate.now())){
            throw   new RuntimeException("Check-in date cannot be in the past");
        }


        if(!request.getCheckOut().isAfter(request.getCheckIn())){
            throw new RuntimeException("Check-out date must be after check-in date");
        }

        long days = ChronoUnit.DAYS.between(request.getCheckIn(),request.getCheckOut());

        Double totalPrice = room.getPrice()*days;

        Booking booking = new Booking();

        booking.setGuest(guest);
        booking.setRoom((room));
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setTotalPrice(totalPrice);
        booking.setStatus("BOOKED");

        room.setStatus("OCCUPIED");
        roomRepository.save(room);

        booking = bookingRepository.save(booking);
        return booking;
    }

    public List<Booking> getAll(){
        return bookingRepository.findAll();
    }






}
