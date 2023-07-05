package com.example.guest.management.controllers;

import com.example.guest.management.domain.Guest;
import com.example.guest.management.domain.Room;
import com.example.guest.management.domain.RoomStatus;
import com.example.guest.management.services.GuestService;
import com.example.guest.management.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;
    private final RoomService roomService;
    @Autowired
    public GuestController(GuestService guestService, RoomService roomService) {
        this.guestService = guestService;
        this.roomService = roomService;
    }

    @GetMapping("/registerGuest")
    public String showAddNannyForm(Model model) {
        model.addAttribute("guest", new Guest());
        return "register";
    }

    @PostMapping("/registerGuest")
    public String saveGuest(@ModelAttribute("guest") Guest guest) {
        // Check if the room is available
        Room room = roomService.findAvailableRoom();
        if (room != null) {
            // Assign the guest to the available room
            guest.setRoom(room);
            room.setGuest(guest);
            room.setStatus(RoomStatus.OCCUPIED);
            guest.setRoom(room); // Set the room number in the guest entity

            // Save the guest and update the room
            guestService.save(guest);
            roomService.save(room);
        } else {
            System.out.println("No available room");
        }

        return "redirect:/guests/registerGuest";
    }

}
