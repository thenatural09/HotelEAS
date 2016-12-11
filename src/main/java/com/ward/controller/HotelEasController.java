package com.ward.controller;

import com.sun.istack.internal.Nullable;
import com.ward.entities.*;
import com.ward.services.*;
import com.ward.utilities.PasswordStorage;
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
    public String home(Model model, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Iterable<Room> roomList = rooms.findAll();
        Iterable<Guest> guestList = guests.findAll();
        model.addAttribute("rooms",roomList);
        model.addAttribute("guests",guestList);
        model.addAttribute("user",user);
        return "home";
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
        rooms.save(room);
        return "redirect:/";
    }

    @RequestMapping(path = "/create-guest", method = RequestMethod.POST)
    public String createGuest(HttpSession session, String firstName, String lastName, Integer numberOfGuests, String notes, String homeAddress, String phoneNumber, Integer numberOfStays, String arrival, String departure, String email, String checkInTime, String checkOutTime, @Nullable Room room, String type, String groupName, String thirdPartyName) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Guest guest = new Guest(firstName,lastName,numberOfGuests,notes,homeAddress,phoneNumber,numberOfStays,user,LocalDate.parse(arrival),LocalDate.parse(departure),email,LocalTime.parse(checkInTime),LocalTime.parse(checkOutTime));
        guests.save(guest);
        return "redirect:/guests";
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
    public String createCreditCard(HttpSession session,String type, int number, LocalDate expirationDate, String billingAddress,String firstName,String lastName) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        CreditCard creditCard = new CreditCard(type,number,expirationDate,billingAddress,guests.findFirstByFirstNameAndLastName(firstName, lastName),user);
        creditCards.save(creditCard);
        return "redirect:/";
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
    public String guests(HttpSession session,Model model,Integer id) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Iterable<Guest> guestList = guests.findAll();
        Iterable<Room> roomList = rooms.findAll();
        model.addAttribute("rooms",roomList);
        model.addAttribute("guests",guestList);
        model.addAttribute("user",user);
        return "guests";
    }

    @RequestMapping(path = "/assign-to-room",method = RequestMethod.POST)
    public String assignToRoom(HttpSession session,Integer id, Integer roomNumber) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Room room = rooms.findFirstByNumber(roomNumber);
        Guest g = guests.findOne(id);
        Guest guest = new Guest(g.getFirstName(),g.getLastName(),g.getNumberOfGuests(),g.getNotes(),g.getHomeAddress(),g.getPhoneNumber(),g.getNumberOfStays(),user,LocalDate.parse(g.getArrival().toString()),LocalDate.parse(g.getDeparture().toString()),g.getEmail(),LocalTime.parse(g.getCheckInTime().toString()),LocalTime.parse(g.getCheckOutTime().toString()),room);
        guests.save(guest);
        return "redirect:/guests";
    }

    @RequestMapping(path = "/assign-to-room",method = RequestMethod.GET)
    public String getAssignRoom(Model model, Integer id) {
        Guest guest = guests.findOne(id);
        model.addAttribute("guest",guest);
        return "assign";
    }

}
