package com.example.guest.management.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class History {
    @Id
    private Long guestId;
    private String name;
    private String surname;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @Column(name = "checkin_time")
    private LocalDateTime checkinTime;
    @Column(name = "checkout_time")
    private LocalDateTime checkoutTime;

    // getters, and setters
    public Long getId() {
        return guestId;
    }

    public void setId(Long id) {
        this.guestId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }
}
