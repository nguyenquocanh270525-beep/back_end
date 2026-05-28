package com.example.rewebdemo.service;

import com.example.rewebdemo.dto.CreateGuestRequest;
import com.example.rewebdemo.dto.UpdateGuestRequest;
import com.example.rewebdemo.entity.Guest;

import com.example.rewebdemo.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public Guest createGuest ( CreateGuestRequest request){
        Guest guest = new Guest();
        guest.setGuestName(request.getGuestName());
        guest.setGender(request.getGender());

        guest = guestRepository.save(guest);
        return guest;

    }

    public List<Guest> getAll(){
        return guestRepository.findAll();
    }


    public Guest getGuest( Long guestId){

        return guestRepository.findByGuestId(guestId);

    }

    public Guest updateGuest(Long guestId, UpdateGuestRequest request){
        Guest guest  = guestRepository.findByGuestId(guestId);

        if(guest == null)return null;
        guest.setGuestName(request.getGuestName());
        guest.setGender(request.getGender());
        guestRepository.save(guest);
        return guest;
    }

    public Guest deleteGuest(Long guestId) {
        Guest guest = guestRepository.findByGuestId(guestId);
        if (guest == null) return null;
        guestRepository.delete(guest);
        return guest;

    }


}
