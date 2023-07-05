package com.example.guest.management.services;

import com.example.guest.management.domain.Room;
import com.example.guest.management.domain.RoomStatus;
import com.example.guest.management.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room findAvailableRoom() {
        List<Room> availableRooms = roomRepository.findByStatus(RoomStatus.AVAILABLE);
        if (!availableRooms.isEmpty()) {
            return availableRooms.get(0);
        }
        return null; // No available room found
    }

    public void save(Room room) {
        roomRepository.save(room);
    }
}
