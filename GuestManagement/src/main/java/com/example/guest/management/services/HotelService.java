package com.example.guest.management.services;

import com.example.guest.management.domain.Hotel;
import com.example.guest.management.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void saveHotel() {
        Hotel hotel = new Hotel();
        // Set other properties of the hotel if needed

        hotelRepository.save(hotel);
    }
}
