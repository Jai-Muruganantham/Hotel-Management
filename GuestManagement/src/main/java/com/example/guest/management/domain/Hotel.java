package com.example.guest.management.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Entity
public class Hotel {
    @Id
    private int hotelId;
    private int totalRooms;
    private int availableRooms;
    private int occupiedRooms;

    public Hotel() {
        this.hotelId = 1; // Default hotel ID
        this.totalRooms = 5; // Default number of rooms
        this.availableRooms = 5; // Default number of available rooms
        this.occupiedRooms = 0; // Default number of occupied rooms
    }

    // Getters and Setters

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public int getOccupiedRooms() {
        return occupiedRooms;
    }

    public void setOccupiedRooms(int occupiedRooms) {
        this.occupiedRooms = occupiedRooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", totalRooms=" + totalRooms +
                ", availableRooms=" + availableRooms +
                ", occupiedRooms=" + occupiedRooms +
                '}';
    }
}
