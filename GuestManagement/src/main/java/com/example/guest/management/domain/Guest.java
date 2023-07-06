package com.example.guest.management.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guest_id;

    private String name;
    private String surname;
    @Column(name = "checkin_time")
    private LocalDateTime checkinTime;

    @OneToOne
    @JoinColumn(name = "room_number")
    private Room room;
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    // Constructors, getters, and setters


    public Long getId() {
        return guest_id;
    }

    public void setId(Long id) {
        this.guest_id = id;
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



    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }


    @Override
    public String toString() {
        return "Guest{" +
                "guest_id=" + guest_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", checkinTime=" + checkinTime +
                ", room=" + room +
                '}';
    }
}
