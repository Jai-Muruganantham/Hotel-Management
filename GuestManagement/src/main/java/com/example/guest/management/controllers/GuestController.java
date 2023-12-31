package com.example.guest.management.controllers;

import com.example.guest.management.domain.*;
import com.example.guest.management.repositories.GuestRepository;
import com.example.guest.management.repositories.HistoryRepository;
import com.example.guest.management.repositories.HotelRepository;
import com.example.guest.management.services.GuestService;
import com.example.guest.management.services.HistoryService;
import com.example.guest.management.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;
    private final RoomService roomService;
    private final HistoryService historyService;
    private final GuestRepository guestRepository;
    private final HistoryRepository historyRepository;
    private final HotelRepository hotelRepository;
    @Autowired
    public GuestController(GuestService guestService, RoomService roomService, HistoryService historyService, GuestRepository guestRepository, HistoryRepository historyRepository, HotelRepository hotelRepository) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.historyService = historyService;
        this.guestRepository = guestRepository;
        this.historyRepository = historyRepository;
        this.hotelRepository = hotelRepository;
    }
    @GetMapping("/registerGuest")
    public String showAddNannyForm(Model model) {
        model.addAttribute("guest", new Guest());
        return "register";
    }
    @PostMapping("/registerGuest")
    public String saveGuest(@ModelAttribute("guest") Guest guest, Model model) {
        // Check if the room is available
        Room room = roomService.findAvailableRoom();
        if (room != null) {
            // Assign the guest to the available room
            room.setStatus(RoomStatus.OCCUPIED);
            guest.setRoom(room); // Set the room number in the guest entity
            guest.setCheckinTime(LocalDateTime.now());
            // Save the guest and update the room
            guestService.save(guest);
            roomService.save(room);
            // Update hotel room counts
            Hotel hotel = hotelRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Hotel not found"));
            hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
            hotel.setOccupiedRooms(hotel.getOccupiedRooms() + 1);
            hotelRepository.save(hotel);
            //Add the guest details to the model
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
            model.addAttribute("message", "Invalid guest ID or guest has already checked out");
        }
        return "checkout"; // Return the name of the HTML file (checkout.html)
    }
    @PostMapping("/checkout")
    public String checkoutGuest(@RequestParam("guestId") Long guestId, Model model) {
        // Find the guest by ID
        Guest guest = guestService.findById(guestId);

        if (guest != null && guest.getRoom() != null) {
            Room room = guest.getRoom();
            room.setStatus(RoomStatus.AVAILABLE);
            // Save the updated room
            roomService.save(room);
            History history = new History();
            history.setId(guest.getId());
            history.setName(guest.getName());
            history.setSurname(guest.getSurname());
            history.setRoom(guest.getRoom());
            history.setCheckinTime(guest.getCheckinTime());
            history.setCheckoutTime(LocalDateTime.now());
            historyService.save(history);
            guestService.delete(guest);
            Hotel hotel = hotelRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Hotel not found"));
            hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
            hotel.setOccupiedRooms(hotel.getOccupiedRooms() - 1);
            hotelRepository.save(hotel);
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
    @GetMapping("/roomOccupancy")
    public String viewOccupancy(Model model) {
        List<Guest> guests = guestRepository.findAll(); // Fetch all guests from the repository
        model.addAttribute("guests", guests); // Pass the guests list to the Thymeleaf template
        return "occupancy"; // Return the name of the Thymeleaf template for rendering
    }
    @GetMapping("/roomHistory")
    public String viewHistory(Model model) {
        List<History> history = historyRepository.findAll(); // Fetch all guests from the repository
        model.addAttribute("histories", history); // Pass the guests list to the Thymeleaf template
        return "history"; // Return the name of the Thymeleaf template for rendering
    }
}
