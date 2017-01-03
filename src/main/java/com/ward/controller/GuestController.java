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
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Troy on 1/2/17.
 */
@Controller
public class GuestController {
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

    @RequestMapping(path = "/create-guest", method = RequestMethod.POST)
    public String createGuest(HttpSession session, String firstName, String lastName, Integer numberOfGuests, String notes, String homeAddress, String phoneNumber, Integer numberOfStays, String arrival, String departure, String email, String checkInTime, String checkOutTime, Integer roomNumber) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Guest guest = new Guest(firstName,lastName,numberOfGuests,notes,homeAddress,phoneNumber,numberOfStays,user, LocalDate.parse(arrival),LocalDate.parse(departure),email, LocalTime.parse(checkInTime),LocalTime.parse(checkOutTime),rooms.findFirstByNumber(roomNumber));
        if (roomNumber == 0) {
            rooms.findFirstByNumber(roomNumber).setHasGuest(false);
            guest.setAssigned(false);
            guest.setHasCreditCard(false);
            guest.setInGroup(false);
            guests.save(guest);
            return "redirect:/unassigned-guests";
        } else {
            rooms.findFirstByNumber(roomNumber).setHasGuest(true);
            guest.setAssigned(true);
            guest.setHasCreditCard(false);
            guest.setInGroup(false);
            guests.save(guest);
            return "redirect:/guests";
        }
    }

    @RequestMapping(path = "/create-guest", method = RequestMethod.GET)
    public String getGuest(Model model) {
        Iterable<Room> roomList = rooms.findAll();
        model.addAttribute("rooms",roomList);
        return "guest";
    }

    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public String guests(HttpSession session, Model model,String search) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Iterable<Guest> guestList = guests.findAll();
        if (search != null) {
            guestList = guests.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search,search,search);
        }
        model.addAttribute("guests",guestList);
        model.addAttribute("user",user);
        return "guests";
    }

    @RequestMapping(path = "/unassigned-guests", method = RequestMethod.GET)
    public String unassignedGuests(HttpSession session, Model model,String search) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Iterable<Guest> guestList = guests.findAll();
        if (search != null) {
            guestList = guests.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search,search,search);
        }
        model.addAttribute("guests",guestList);
        model.addAttribute("user",user);
        return "unassigned-guests";
    }

    @RequestMapping(path = "/remove-guest",method = RequestMethod.POST)
    public String removeGuest(HttpSession session,Integer id) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        guests.findOne(id).getRoom().setHasGuest(false);
        guests.delete(id);
        return "redirect:/guests";
    }
    @RequestMapping(path = "/go-to-guests",method = RequestMethod.POST)
    public String goToGuests(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        return "redirect:/guests";
    }

    @RequestMapping(path = "/go-to-unassigned-guests",method = RequestMethod.POST)
    public String goToUnGuests(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        return "redirect:/unassigned-guests";
    }
}
