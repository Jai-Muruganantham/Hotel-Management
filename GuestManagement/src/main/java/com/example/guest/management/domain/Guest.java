package com.example.guest.management.domain;

import jakarta.persistence.*;


@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guest_id;

    private String name;
    private String surname;

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

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + guest_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", room=" + room +
                '}';
    }
}
