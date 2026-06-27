package com.example.rewebdemo.service;

import com.example.rewebdemo.dto.CreateGuestRequest;
import com.example.rewebdemo.dto.UpdateGuestRequest;
import com.example.rewebdemo.entity.Guest;
import com.example.rewebdemo.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public List<Guest> getAll(){
        return guestRepository.findAll();
    }

    public Guest getId(Long id){
        return guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    public Guest createGuest(CreateGuestRequest request){
        Guest guest = new Guest();

        guest.setGuestName(request.getGuestName());
        guest.setGuestEmail(request.getGuestEmail());
        guest.setGuestPhone(request.getGuestPhone());
        guest.setGender(request.getGender());

        guest = guestRepository.save(guest);
        return guest;

    }

    public Guest updateGuest(UpdateGuestRequest request, Long id){

        Guest  guest = guestRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

        guest.setGuestName(request.getGuestName());
        guest.setGender(request.getGender());

        guest = guestRepository.save(guest);

        return guest;
    }

    public Guest deleteGuest (Long id){
        Guest guest  = guestRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

         guestRepository.deleteById(id);
         return guest;


    }







}
