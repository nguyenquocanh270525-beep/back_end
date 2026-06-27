package com.example.rewebdemo.controller;


import com.example.rewebdemo.dto.CreateGuestRequest;
import com.example.rewebdemo.dto.UpdateGuestRequest;
import com.example.rewebdemo.entity.Guest;
import com.example.rewebdemo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guests")
public class GuestController {

   @Autowired
    GuestService guestService;

   @PostMapping
   public Guest createGuest(@RequestBody CreateGuestRequest request){
       return guestService.createGuest(request);
   }

   @GetMapping
    public List<Guest> getAll(){
       return guestService.getAll();
   }

   @GetMapping("{id}")
    public Guest getId(@PathVariable Long id){
       return guestService.getId(id);
   }

   @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id,
                             @RequestBody UpdateGuestRequest request){
       return guestService.updateGuest(request, id);
   }

   @DeleteMapping("/{id}")
    public Guest deleteGuest(@PathVariable Long id){
       return guestService.deleteGuest(id);
   }






}

