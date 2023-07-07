package com.example.guest.management.repositories;

import com.example.guest.management.domain.Guest;
import com.example.guest.management.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByRoom(Room room);
}
