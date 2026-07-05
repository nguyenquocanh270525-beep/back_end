package com.example.rewebdemo.service;

import com.example.rewebdemo.dto.CreateRoomRequest;
import com.example.rewebdemo.dto.UpdateRoomRequest;
import com.example.rewebdemo.dto.UpdateRoomStatusRequest;
import com.example.rewebdemo.entity.Room;
import com.example.rewebdemo.enums.RoomStatus;
import com.example.rewebdemo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public Room createRoom(CreateRoomRequest request){

        if (roomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new RuntimeException("Room number already exists");
        }

        Room room = new Room();

        room.setRoomNumber(request.getRoomNumber());
        room.setRoomType(request.getRoomType());
        room.setPrice(request.getPrice());
        room.setFloor(request.getFloor());
        room.setCapacity(request.getCapacity());
        room.setStatus(RoomStatus.AVAILABLE);
        room = roomRepository.save(room);

        return room;

    }

    public List<Room> getAll(){
        return roomRepository.findAll();
    }

    public Room getId(Long id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

    }

    public Room updateRoom (Long id, UpdateRoomRequest request){
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));

        room.setRoomType(request.getRoomType());
        room.setPrice(request.getPrice());
        room.setCapacity(request.getCapacity());

        room = roomRepository.save(room);
        return room;

    }

    public Room updateRoomStatus(Long id, UpdateRoomStatusRequest request){
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));

        room.setStatus(RoomStatus.valueOf(request.getStatus()));
        room = roomRepository.save(room);

        return room;
    }

    public Room deleteRoom (Long id ) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        roomRepository.deleteById(id);
        return room;

    }
}


