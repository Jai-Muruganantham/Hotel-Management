package com.example.guest.management;

import com.example.guest.management.services.HotelService;
import com.example.guest.management.services.RoomService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuestManagementApplication {
	private final RoomService roomService;
	private final HotelService hotelService;

	@Autowired
	public GuestManagementApplication(RoomService roomService, HotelService hotelService) {
		this.roomService = roomService;
		this.hotelService = hotelService;
	}
	public static void main(String[] args) {
		SpringApplication.run(GuestManagementApplication.class, args);
	}
	// Invoke the creation of default rooms
	@PostConstruct
	public void init() {
		roomService.createDefaultRooms();
		hotelService.createDefaultHotel();
	}
}
