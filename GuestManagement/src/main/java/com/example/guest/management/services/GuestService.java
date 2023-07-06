package com.example.guest.management.services;

import com.example.guest.management.domain.Guest;
import com.example.guest.management.domain.History;
import com.example.guest.management.domain.Room;
import com.example.guest.management.repositories.GuestRepository;
import com.example.guest.management.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HistoryRepository historyRepository;


    public List<Guest> findAll() {
        return guestRepository.findAll();
    }


    public void delete(Guest guest) {
        // Delete associated history records first
        List<History> historyRecords = historyRepository.findByGuestId(guest.getId());
        historyRepository.deleteAll(historyRecords);

        // Delete the guest
        guestRepository.delete(guest);
    }

    public Guest findById(Long id) {
        return guestRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        guestRepository.deleteById(id);
    }
    public void save(Guest guest) {
        guestRepository.save(guest);
    }
}
