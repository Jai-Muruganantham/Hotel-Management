package com.example.guest.management.repositories;

import com.example.guest.management.domain.Room;
import com.example.guest.management.domain.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByStatus(RoomStatus status);

    // Add any additional custom query methods as needed
}
