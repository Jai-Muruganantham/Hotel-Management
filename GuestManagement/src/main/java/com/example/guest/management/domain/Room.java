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

    // getters, and setters
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
