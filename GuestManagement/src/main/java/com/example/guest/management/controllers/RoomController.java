package com.example.guest.management.controllers;

import com.example.guest.management.domain.Room;
import com.example.guest.management.domain.Hotel;
import com.example.guest.management.repositories.HotelRepository;
import com.example.guest.management.repositories.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequestMapping("/room")
public class RoomController {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomController(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }
    @GetMapping("/roomStatus")
    public String showAddNannyForm(Model model) {

        List<Room> room=roomRepository.findAll();
        List<Hotel> hotel=hotelRepository.findAll();

        model.addAttribute("rooms",room);
        model.addAttribute("details",hotel);
        return "roomStatus";
    }

}
