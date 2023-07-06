package com.example.guest.management.controllers;

import com.example.guest.management.domain.Room;
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

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/roomStatus")
    public String showAddNannyForm(Model model) {

        List<Room> room=roomRepository.findAll();

        model.addAttribute("rooms",room);
        return "roomStatus";
    }

}
