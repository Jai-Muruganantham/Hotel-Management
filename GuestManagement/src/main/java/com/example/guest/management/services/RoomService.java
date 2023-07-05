package com.example.guest.management.services;


import com.example.guest.management.domain.Room;
import com.example.guest.management.domain.RoomStatus;
import com.example.guest.management.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private List<Room> rooms;
    @Autowired
    private RoomRepository roomRepository;
    public RoomService() {
        // Initialize rooms with default values
        rooms = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Room room = new Room();
            room.setRoomNumber(i);
            room.setStatus(RoomStatus.AVAILABLE);
            rooms.add(room);
        }
    }

    public Room findAvailableRoom() {
        for (Room room : rooms) {
            if (room.getStatus() == RoomStatus.AVAILABLE) {
                return room;
            }
        }
        return null; // No available room found
    }

    public void save(Room room) {
        // No specific action required to save the room in this example
        roomRepository.save(room);
    }
}
