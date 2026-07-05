package com.example.rewebdemo.service;

import com.example.rewebdemo.dto.CreateBookingRequest;
import com.example.rewebdemo.dto.UpdateBookingRequest;
import com.example.rewebdemo.entity.Booking;
import com.example.rewebdemo.entity.Guest;
import com.example.rewebdemo.entity.Room;
import com.example.rewebdemo.enums.BookingStatus;
import com.example.rewebdemo.enums.RoomStatus;
import com.example.rewebdemo.repository.BookingRepository;
import com.example.rewebdemo.repository.GuestRepository;
import com.example.rewebdemo.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
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


    @Transactional
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

        List<Booking> bookings = bookingRepository.findByRoom(room);

        for (Booking otherBooking : bookings){
            if(otherBooking.getStatus() == BookingStatus.CANCELLED){
                continue;
            }
            if(request.getCheckIn().isBefore(otherBooking.getCheckOut()) && request.getCheckOut().isAfter(otherBooking.getCheckIn())){
                throw new RuntimeException("Room has already been booked during this period");
            }

        }


        long days = ChronoUnit.DAYS.between(request.getCheckIn(),request.getCheckOut());

        Double totalPrice = room.getPrice()*days;

        Booking booking = new Booking();

        booking.setGuest(guest);
        booking.setRoom((room));
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.BOOKED);

        room.setStatus(RoomStatus.OCCUPIED);
        roomRepository.save(room);

        booking = bookingRepository.save(booking);
        return booking;
    }

    public List<Booking> getAll(){
        return bookingRepository.findAll();
    }

    public Booking getId(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

    }


    public Booking checkIn(Long id ){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(booking.getStatus() != BookingStatus.BOOKED){
            throw new RuntimeException("Only BOOKED bookings can check in");
        }



        booking.setStatus(BookingStatus.CHECKED_IN);

        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking checkOut(Long id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not Found"));

        if(booking.getStatus() != BookingStatus.CHECKED_IN){
            throw new RuntimeException("Only CHECKED_IN bookings can check out");
        }

        booking.setStatus(BookingStatus.CHECKED_OUT);

        Room room = booking.getRoom();

        room.setStatus(RoomStatus.AVAILABLE);

        roomRepository.save(room);

        return bookingRepository.save(booking);

    }
    @Transactional
    public Booking cancelBooking(Long id){

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() != BookingStatus.BOOKED) {
            throw new RuntimeException("Only BOOKED bookings can be cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Room room = booking.getRoom();
        room.setStatus(RoomStatus.AVAILABLE);

        roomRepository.save(room);

        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, UpdateBookingRequest request){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(booking.getStatus() != BookingStatus.BOOKED){
            throw new RuntimeException("Khoong duoc cap nhat");
        }

        if(request.getCheckIn().isBefore(LocalDate.now())){
            throw new RuntimeException("Check-in date cannot be in the past");
        }

        if(!request.getCheckOut().isAfter(request.getCheckIn())){
            throw new RuntimeException("Check-out date must be after check-in date");
        }

        List<Booking> bookings = bookingRepository.findByRoom(booking.getRoom());

        for (Booking otherBooking : bookings){
            if(otherBooking.getId().equals(booking.getId())){
                continue;
            }
            if(otherBooking.getStatus() == BookingStatus.CANCELLED){
                continue;
            }
            if(request.getCheckIn().isBefore(otherBooking.getCheckOut()) && request.getCheckOut().isAfter(otherBooking.getCheckIn())){
                throw new RuntimeException("Room has already been booked during this period");
            }

        }

        long days = ChronoUnit.DAYS.between(request.getCheckIn(),request.getCheckOut());

        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setTotalPrice(days * booking.getRoom().getPrice());

        return bookingRepository.save(booking);
    }















}
