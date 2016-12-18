package com.ward.controller;

import com.sun.istack.internal.Nullable;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ward.entities.*;
import com.ward.services.*;
import com.ward.utilities.PasswordStorage;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Troy on 12/6/16.
 */
@Controller
public class HotelEasController {
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

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, Integer numberOfBeds) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        List<Room> roomList;
        Iterable<Guest> guestList = guests.findAll();
        if (numberOfBeds != null) {
            roomList = rooms.findByNumberOfBeds(numberOfBeds);
        } else {
            roomList = rooms.findByOrderByNumberDesc();
        }
        model.addAttribute("rooms",roomList);
        model.addAttribute("guests",guestList);
        model.addAttribute("user",user);
        return "home";
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

    @RequestMapping(path = "/go-to-guests",method = RequestMethod.POST)
    public String goToGuests(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        return "redirect:/guests";
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

    @RequestMapping(path = "/go-to-unassigned-guests",method = RequestMethod.POST)
    public String goToUnGuests(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        return "redirect:/unassigned-guests";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String signup(String username,String password,HttpSession session) throws Exception {
        User user = new User(username, PasswordStorage.createHash(password));
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new Exception("Invalid signup try");
        }
        users.save(user);
        session.setAttribute("username",username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username,String password,HttpSession session) throws Exception {
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Invalid login try");
        }
        else if (user.getUsername() == null || user.getPassword() == null) {
            throw new Exception("Invalid login try");
        }
        else if (!PasswordStorage.verifyPassword(password,user.getPassword())) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("username",username);
        return "redirect:/";
    }

    @RequestMapping(path = "/create-room", method = RequestMethod.POST)
    public String createRoom(HttpSession session,int number,double rate,int numberOfBeds,String type) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Room room = new Room(number,rate,numberOfBeds,type,user);
        room.setHasGuest(false);
        room.setClean(false);
        rooms.save(room);
        return "redirect:/";
    }

    @RequestMapping(path = "/create-guest", method = RequestMethod.POST)
    public String createGuest(HttpSession session, String firstName, String lastName, Integer numberOfGuests, String notes, String homeAddress, String phoneNumber, Integer numberOfStays, String arrival, String departure, String email, String checkInTime, String checkOutTime, Integer roomNumber) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Guest guest = new Guest(firstName,lastName,numberOfGuests,notes,homeAddress,phoneNumber,numberOfStays,user,LocalDate.parse(arrival),LocalDate.parse(departure),email,LocalTime.parse(checkInTime),LocalTime.parse(checkOutTime),rooms.findFirstByNumber(roomNumber));
        if (roomNumber == 0) {
            rooms.findFirstByNumber(roomNumber).setHasGuest(false);
            guest.setAssigned(false);
            guest.setHasCreditCard(false);
            guests.save(guest);
            return "redirect:/unassigned-guests";
        } else {
            rooms.findFirstByNumber(roomNumber).setHasGuest(true);
            guest.setAssigned(true);
            guest.setHasCreditCard(false);
            guests.save(guest);
            return "redirect:/guests";
        }
    }

    @RequestMapping(path = "/create-third-party", method = RequestMethod.POST)
    public String createThirdParty(HttpSession session,String name, boolean hasPrePay, double rate) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        ThirdParty thirdParty = new ThirdParty(name,hasPrePay,rate);
        thirdParties.save(thirdParty);
        return "redirect:/";
    }

    @RequestMapping(path = "/create-credit-card", method = RequestMethod.POST)
    public String createCreditCard(Integer id,HttpSession session,String type, int number, String expirationDate, String billingAddress,String firstName,String lastName) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Guest guest = guests.findOne(id);
        CreditCard creditCard = new CreditCard(type,number,LocalDate.parse(expirationDate),billingAddress,user);
        guest.setCreditCard(creditCard);
        guest.setHasCreditCard(true);
        creditCards.save(creditCard);
        return "redirect:/guests";
    }

    @RequestMapping(path ="/create-credit-card",method = RequestMethod.GET)
    public String getCreditCard(Integer id,HttpSession session) {
        Guest guest = guests.findOne(id);
        return "create-credit-card";
    }

    @RequestMapping(path = "/create-group", method = RequestMethod.POST)
    public String createGroup(HttpSession session,String name, int numberOfRooms,double discount, String event, LocalDate arrival, LocalDate departure,int roomNumber) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Group group = new Group(name,numberOfRooms,rooms.findFirstByNumber(roomNumber),discount,event,arrival,departure,user);
        groups.save(group);
        return "redirect:/";
    }

    @RequestMapping(path = "/create-room", method = RequestMethod.GET)
    public String getRoom(Model model) {
        return "room";
    }

    @RequestMapping(path = "/create-guest", method = RequestMethod.GET)
    public String getGuest(Model model) {
        return "guest";
    }

    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public String guests(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Iterable<Guest> guestList = guests.findAll();
        model.addAttribute("guests",guestList);
        model.addAttribute("user",user);
        return "guests";
    }

    @RequestMapping(path = "/unassigned-guests", method = RequestMethod.GET)
    public String unassignedGuests(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Iterable<Guest> guestList = guests.findAll();
        model.addAttribute("guests",guestList);
        model.addAttribute("user",user);
        return "unassigned-guests";
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
}
