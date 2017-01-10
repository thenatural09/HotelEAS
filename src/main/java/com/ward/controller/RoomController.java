package com.ward.controller;

import com.ward.entities.Guest;
import com.ward.entities.Room;
import com.ward.entities.User;
import com.ward.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Troy on 1/2/17.
 */
@Controller
public class RoomController {
    @Autowired
    CreditCardRepository creditCards;

    @Autowired
    GroupRepository groups;

    @Autowired
    GuestRepository guests;

    @Autowired
    RoomRepository rooms;

    @Autowired
    ThirdPartyRepository thirdParties;

    @Autowired
    UserRepository users;

    @Autowired
    RateRepository rates;

    @RequestMapping(path = "/create-room", method = RequestMethod.POST)
    public String createRoom(HttpSession session, int number, double rate, int numberOfBeds, String type) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Room room = new Room(number,numberOfBeds,type,user);
        room.setHasGuest(false);
        room.setClean(false);
        room.setHasRates(false);
        rooms.save(room);
        return "redirect:/";
    }

    @RequestMapping(path = "/create-room", method = RequestMethod.GET)
    public String getRoom(Model model) {
        return "room";
    }

    @RequestMapping(path = "/assign-to-room",method = RequestMethod.POST)
    public String assignToRoom(HttpSession session,Integer id, Integer roomNumber) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Guest g = guests.findOne(id);
        g.getRoom().setHasGuest(false);
        Room room = rooms.findFirstByNumber(roomNumber);
        if (room == null) {
            return "redirect:/guests";
        }
        g.setRoom(room);
        if (room.getNumber() == 0) {
            room.setHasGuest(false);
            g.setAssigned(false);
            guests.save(g);
            return "redirect:/unassigned-guests";
        } else {
            room.setHasGuest(true);
            g.setAssigned(true);
            guests.save(g);
            return "redirect:/guests";
        }
    }

    @RequestMapping(path = "/assign-to-room",method = RequestMethod.GET)
    public String getAssignRoom(Model model, Integer id) {
        Guest guest = guests.findOne(id);
        Iterable<Room> roomList = rooms.findAll();
        model.addAttribute("rooms",roomList);
        model.addAttribute("guest",guest);
        return "assign";
    }

    @RequestMapping(path = "/set-is-clean",method = RequestMethod.POST)
    public String setIsClean(HttpSession session,Integer id) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Room room = rooms.findOne(id);
        room.setClean(true);
        rooms.save(room);
        return "redirect:/";
    }

    @RequestMapping(path = "/set-is-dirty",method = RequestMethod.POST)
    public String setIsDirty(HttpSession session,Integer id) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Room room = rooms.findOne(id);
        room.setClean(false);
        rooms.save(room);
        return "redirect:/";
    }

    @RequestMapping(path = "/go-to-rooms",method = RequestMethod.POST)
    public String goToRooms(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        return "redirect:/";
    }
}
