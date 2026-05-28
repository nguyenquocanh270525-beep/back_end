package com.example.rewebdemo.controller;

import com.example.rewebdemo.dto.CreateGuestRequest;
import com.example.rewebdemo.dto.UpdateGuestRequest;
import com.example.rewebdemo.entity.Guest;
import com.example.rewebdemo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/guests")
public class guestController {

    List<Guest> guests = new ArrayList<>();

    @Autowired
    GuestService guestService;

    @PostMapping("")
    public Guest createGuest(@RequestBody CreateGuestRequest request){
        return guestService.createGuest(request);
    }

    @GetMapping("")
    public List<Guest> getAll(){
        return guestService.getAll();
    }

    @GetMapping("/{guestId}")
    public Guest getGuest(@PathVariable Long guestId){
        return guestService.getGuest(guestId);


    }

    @PutMapping("/{guestId}")
    public Guest updateGuest(@PathVariable Long guestId,
                             @RequestBody UpdateGuestRequest request){
        return guestService.updateGuest(guestId, request);

    }

    @DeleteMapping("/{guestId}")
    public Guest deleteGuest(@PathVariable Long guestId){
        return guestService.deleteGuest(guestId);
    }
}

