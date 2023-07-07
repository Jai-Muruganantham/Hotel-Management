package com.example.guest.management.repositories;

import com.example.guest.management.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByGuestId(Long guestId);
}
