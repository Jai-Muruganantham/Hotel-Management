package com.example.guest.management.services;

import com.example.guest.management.domain.Hotel;
import com.example.guest.management.domain.Room;
import com.example.guest.management.domain.RoomStatus;
import com.example.guest.management.repositories.HotelRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @PostConstruct
    public void createDefaultHotel() {
        if (hotelRepository.count() == 0) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(1L);
                hotel.setAvailableRooms(5);
                hotel.setOccupiedRooms(0);
                hotel.setTotalRooms(5);
                hotelRepository.save(hotel);
            }
        }
}
