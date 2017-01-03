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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException {
        User defaultUser = new User("Troy", PasswordStorage.createHash("pass123"));
        if (users.findFirstByUsername("Troy") == null) {
            users.save(defaultUser);
        }
        ArrayList<Room> roomList = new ArrayList<>();
        roomList.add(new Room(0,0,0,"Waiting To Be Assigned",defaultUser,false,true));
        roomList.add(new Room(201,750,2,"Residential Suite",defaultUser,true,true));
        roomList.add(new Room(301,500,1,"Suite",defaultUser,false,true));
        roomList.add(new Room(321,1200,3,"Residential Suite",defaultUser,false,true));
        roomList.add(new Room(231,250,1,"Studio",defaultUser,false,true));
        roomList.add(new Room(531,300,1,"Signature Studio",defaultUser,false,true));
        if(rooms.findFirstByNumber(0) == null) {
            rooms.save(roomList);
        }
        ArrayList<Guest> guestList = new ArrayList<>();
        guestList.add(new Guest("Troy","Ward",2,"Needy","93 Smith St","6823513855",2,defaultUser,LocalDate.parse("2016-12-24"),LocalDate.parse("2016-12-25"),"tward4@tulane.edu",LocalTime.parse("15:30"),LocalTime.parse("10:00"),rooms.findFirstByNumber(201),null,true,false,false,null));
        guestList.add(new Guest("Bob","Ward",3,"Helpful","83 Smithfield Rd","9389200202",4,defaultUser,LocalDate.parse("2016-12-24"),LocalDate.parse("2016-12-28"),"bward4@tulane.edu",LocalTime.parse("15:30"),LocalTime.parse("10:00"),rooms.findFirstByNumber(0),null,false,false,false,null));
        guestList.add(new Guest("Paul","Ward",4,"Might Be Late","34 Orange St","3839292929",2,defaultUser,LocalDate.parse("2016-12-24"),LocalDate.parse("2016-12-26"),"pward4@tulane.edu",LocalTime.parse("15:30"),LocalTime.parse("10:00"),rooms.findFirstByNumber(0),null,false,false,false,null));
        guestList.add(new Guest("Carl","Ward",1,"Needy","89 Smith St","238238293023",2,defaultUser,LocalDate.parse("2016-12-24"),LocalDate.parse("2016-12-27"),"cward4@tulane.edu",LocalTime.parse("15:30"),LocalTime.parse("10:00"),rooms.findFirstByNumber(0),null,false,false,false,null));
        if (guests.findFirstByFirstNameAndLastName("Troy","Ward") == null) {
            guests.save(guestList);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, Integer numberOfBeds,Double rate) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        List<Room> roomList;
        Iterable<Guest> guestList = guests.findAll();
        if (numberOfBeds != null) {
            roomList = rooms.findByNumberOfBeds(numberOfBeds);
        }
        else {
            roomList = rooms.findByOrderByNumberDesc();
        }
        model.addAttribute("rooms",roomList);
        model.addAttribute("guests",guestList);
        model.addAttribute("user",user);
        return "home";
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
}
