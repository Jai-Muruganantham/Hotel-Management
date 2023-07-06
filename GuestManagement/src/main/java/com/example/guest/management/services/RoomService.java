package com.example.guest.management.services;

import com.example.guest.management.domain.Room;
import com.example.guest.management.domain.RoomStatus;
import com.example.guest.management.repositories.HotelRepository;
import com.example.guest.management.repositories.RoomRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    private HotelRepository hotelRepository;
    public Room findAvailableRoom() {
        List<Room> availableRooms = roomRepository.findByStatus(RoomStatus.AVAILABLE);
        if (!availableRooms.isEmpty()) {
            return availableRooms.get(0);
        }
        return null; // No available room found
    }
    @PostConstruct
    public void createDefaultRooms() {

        if (roomRepository.count() == 0) {
            for (int i = 1; i <= 5; i++) {
                Room room = new Room();
                room.setRoomNumber(i);
                room.setStatus(RoomStatus.AVAILABLE);

                roomRepository.save(room);
            }
        }
    }
    public void save(Room room) {
        roomRepository.save(room);
    }
}
