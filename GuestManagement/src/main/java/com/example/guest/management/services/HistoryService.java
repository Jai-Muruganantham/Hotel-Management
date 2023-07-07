package com.example.guest.management.services;

import com.example.guest.management.domain.History;
import com.example.guest.management.domain.Hotel;
import com.example.guest.management.repositories.HistoryRepository;
import com.example.guest.management.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void save(History history) {
        historyRepository.save(history);
    }
}
