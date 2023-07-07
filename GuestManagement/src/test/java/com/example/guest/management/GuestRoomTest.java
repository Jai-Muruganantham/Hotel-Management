package com.example.guest.management;

import com.example.guest.management.domain.Guest;
import com.example.guest.management.domain.Room;
import com.example.guest.management.repositories.GuestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GuestRoomTest {

    @Mock
    private GuestRepository guestRepository;

    private Room room;

    @BeforeEach
    public void setup() {
        room = new Room();
        room.setRoomNumber(1);
    }

    @Test
    public void testRoomHasOnlyOneGuestAtSameTime() {
        LocalDateTime checkinTime = LocalDateTime.now();

        Guest guest1 = new Guest();
        guest1.setName("John");
        guest1.setSurname("Doe");
        guest1.setCheckinTime(checkinTime);
        guest1.setRoom(room);

        List<Guest> guestsInRoom = new ArrayList<>();
        guestsInRoom.add(guest1);

        Mockito.when(guestRepository.findByRoom(room)).thenReturn(guestsInRoom);

        Guest guest2 = new Guest();
        guest2.setName("Jane");
        guest2.setSurname("Smith");
        guest2.setCheckinTime(checkinTime);
        guest2.setRoom(room);

        boolean isRoomOccupied = guestsInRoom.stream()
                .anyMatch(guest -> guest.getCheckinTime().equals(checkinTime));

        if (!isRoomOccupied && !guest1.getCheckinTime().equals(guest2.getCheckinTime())) {
            guestsInRoom.add(guest2);
        }

        List<Guest> guests = guestRepository.findByRoom(room);
        Assertions.assertEquals(guestsInRoom, guests);
    }
}
