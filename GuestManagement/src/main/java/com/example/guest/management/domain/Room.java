package com.example.guest.management.domain;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @Column(name = "room_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomNumber;
    @OneToOne(mappedBy = "room")
    private Guest guest;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    // Constructors, getters, and setters
    public Room() {
        Hotel hotel = getHotel(); // Retrieve the hotel instance
        this.roomNumber = 1; // Default room number
        this.status = RoomStatus.AVAILABLE; // Default room status

        if (hotel != null) {
            int totalRooms = hotel.getTotalRooms();
            this.roomNumber = totalRooms + 1; // Increment the room number based on the total rooms in the hotel
            hotel.setTotalRooms(totalRooms + 1); // Update the totalRooms count in the hotel
        }
    }

    // Constructors, getters, and setters...

    private Hotel getHotel() {
        // Implement the logic to retrieve the Hotel instance from the database
        // For example, using JPA EntityManager:
        // EntityManager entityManager = ... // Obtain the entity manager
        // Hotel hotel = entityManager.find(Hotel.class, 1); // Assuming hotelId 1
        // return hotel;

        // In this example, create a new Hotel instance with a default total of 5 rooms
        Hotel hotel = new Hotel();
        hotel.setTotalRooms(5);
        return hotel;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", guest=" + guest +
                ", status=" + status +
                '}';
    }
}
