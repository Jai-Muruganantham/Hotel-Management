package com.example.guest.management.services;

import com.example.guest.management.domain.Guest;
import com.example.guest.management.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest findById(Long id) {
        return guestRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        guestRepository.deleteById(id);
    }
}
