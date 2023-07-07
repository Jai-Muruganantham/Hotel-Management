package com.example.guest.management.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Entity
public class Hotel {
    @Id
    private Long hotelId;
    private int totalRooms;
    private int availableRooms;
    private int occupiedRooms;

    // Getters and Setters

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
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
