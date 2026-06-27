package com.example.rewebdemo.controller;

import com.example.rewebdemo.dto.CreateRoomRequest;
import com.example.rewebdemo.dto.UpdateRoomRequest;
import com.example.rewebdemo.dto.UpdateRoomStatusRequest;
import com.example.rewebdemo.entity.Room;
import com.example.rewebdemo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")

public class RoomController {
    @Autowired
    RoomService roomService;


    @PostMapping
    public Room createRoom(@RequestBody CreateRoomRequest request){
        return roomService.createRoom(request);
    }

    @GetMapping
    public List<Room> getAll() {
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public Room getId(@PathVariable Long id){
        return roomService.getId(id);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@RequestBody UpdateRoomRequest request,
                           @PathVariable Long id){

        return roomService.updateRoom(id, request);
    }

    @PatchMapping("/{id}/status")
    public Room updateRoomStatus(@RequestBody UpdateRoomStatusRequest request,
                                 @PathVariable Long id){
        return roomService.updateRoomStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public Room deleteRoom(@PathVariable Long id){
        return roomService.deleteRoom(id);
    }


}
