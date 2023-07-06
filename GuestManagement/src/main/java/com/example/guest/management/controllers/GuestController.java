package com.example.guest.management.controllers;

import com.example.guest.management.domain.Guest;
import com.example.guest.management.domain.History;
import com.example.guest.management.domain.Room;
import com.example.guest.management.domain.RoomStatus;
import com.example.guest.management.services.GuestService;
import com.example.guest.management.services.HistoryService;
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
    private final HistoryService historyService;
    @Autowired
    public GuestController(GuestService guestService, RoomService roomService, HistoryService historyService) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.historyService = historyService;
    }

    @GetMapping("/registerGuest")
    public String showAddNannyForm(Model model) {
        model.addAttribute("guest", new Guest());
        return "register";
    }

//    @PostMapping("/registerGuest")
//    public String saveGuest(@ModelAttribute("guest") Guest guest) {
//        // Check if the room is available
//        Room room = roomService.findAvailableRoom();
//        if (room != null) {
//            // Assign the guest to the available room
//            guest.setRoom(room);
//            room.setGuest(guest);
//            room.setStatus(RoomStatus.OCCUPIED);
//            guest.setRoom(room); // Set the room number in the guest entity
//
//            // Save the guest and update the room
//            guestService.save(guest);
//            roomService.save(room);
//        } else {
//            System.out.println("No available room");
//        }
//
//        return "redirect:/guests/registerGuest";
//    }
//    @PostMapping("/registerGuest")
//    public String saveGuest(@ModelAttribute("guest") Guest guest, Model model) {
//        // Check if the room is available
//        Room room = roomService.findAvailableRoom();
//        if (room != null) {
//            // Assign the guest to the available room
//            guest.setRoom(room);
//            room.setGuest(guest);
//            room.setStatus(RoomStatus.OCCUPIED);
//            guest.setRoom(room); // Set the room number in the guest entity
//
//            // Save the guest and update the room
//            guestService.save(guest);
//            roomService.save(room);
//            model.addAttribute("message", "Room Reserved");
//            return "registrationResult"; // Redirect to the registration page
//        } else {
//            model.addAttribute("message", "No available room"); // Add the attribute to the model
//            return "registrationResult"; // Return the name of the HTML file
//        }
//    }

    @PostMapping("/registerGuest")
    public String saveGuest(@ModelAttribute("guest") Guest guest, Model model) {
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

            // Add the guest details to the model
            model.addAttribute("message", "Room Reserved");
            model.addAttribute("guestName", guest.getName());
            model.addAttribute("guestSurname", guest.getSurname());
            model.addAttribute("roomNumber", room.getRoomNumber());
            model.addAttribute("guestId", guest.getId());

            return "registrationResult"; // Redirect to the registration page
        } else {
            model.asMap().remove("guestName");
            model.asMap().remove("guestSurname");
            model.asMap().remove("roomNumber");
            model.asMap().remove("guestId");
            model.addAttribute("message", "No available room"); // Add the attribute to the model
            return "registrationResult"; // Return the name of the HTML file
        }
    }
    @GetMapping("/checkoutGuest")
    public String checkout(Model model) {
        //model.addAttribute("guest", new Guest());
        return "checkout";
    }
//    @PostMapping("/checkout")
//    public String checkoutGuest(@RequestParam("guestId") Long guestId, Model model) {
//        // Find the guest by ID
//        Guest guest = guestService.findById(guestId);
//
//        if (guest != null && guest.getRoom() != null) {
//            Room room = guest.getRoom();
//
//            // Clear the guest from the room
//            room.setGuest(null);
//            room.setStatus(RoomStatus.AVAILABLE);
//
//            // Save the updated room
//            roomService.save(room);
//
//            // Remove the guest details from the model
//            model.asMap().remove("guestName");
//            model.asMap().remove("guestSurname");
//            model.asMap().remove("roomNumber");
//            model.asMap().remove("guestId");
//
//            model.addAttribute("message", "Checkout successful");
//        } else {
//            model.addAttribute("message", "Invalid guest ID or guest has already checked out");
//        }
//
//        return "checkoutResult";
//    }



    @GetMapping("/checkout")
    public String showCheckoutForm() {
        return "checkout"; // Return the name of the HTML file (checkout.html)
    }

    @PostMapping("/getGuestDetails")
    public String getGuestDetails(@RequestParam("guestId") Long guestId, Model model) {
        // Find the guest by ID
        Guest guest = guestService.findById(guestId);

        if (guest != null && guest.getRoom() != null) {
            model.addAttribute("guestId", guest.getId());
            model.addAttribute("guestName", guest.getName());
            model.addAttribute("guestSurname", guest.getSurname());
            model.addAttribute("guestRoomNumber", guest.getRoom().getRoomNumber());
        } else {
            model.addAttribute("message", "Invalid guest ID");
        }

        return "checkout"; // Return the name of the HTML file (checkout.html)
    }

    // ...

    @PostMapping("/checkout")
    public String checkoutGuest(@RequestParam("guestId") Long guestId, Model model) {
        // Find the guest by ID
        Guest guest = guestService.findById(guestId);

        if (guest != null && guest.getRoom() != null) {
            Room room = guest.getRoom();

            // Clear the guest from the room
            //room.setGuest(null);
            room.setStatus(RoomStatus.AVAILABLE);

            // Save the updated room
            roomService.save(room);
            History history = new History();
            history.setGuest(guest);
            historyService.save(history);
            guestService.delete(guest);
            // Remove the guest details from the model
            model.asMap().remove("guestName");
            model.asMap().remove("guestSurname");
            model.asMap().remove("roomNumber");
            model.asMap().remove("guestId");

            model.addAttribute("message", "Checkout successful");
        } else {
            model.addAttribute("message", "Invalid guest ID or guest has already checked out");
        }

        return "checkoutResult"; // Return the name of the HTML file (checkoutResult.html)
    }

}
