package com.ward.controller;

import com.ward.entities.Group;
import com.ward.entities.Guest;
import com.ward.entities.User;
import com.ward.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Troy on 1/2/17.
 */
@Controller
public class GroupController {
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

    @RequestMapping(path = "/create-group", method = RequestMethod.POST)
    public String createGroup(Integer id, HttpSession session, String name, double discount, String event, String arrival, String departure) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Group group = new Group(name,discount,event, LocalDate.parse(arrival),LocalDate.parse(departure),user);
        groups.save(group);
        return "redirect:/guests";
    }

    @RequestMapping(path = "/create-group",method = RequestMethod.GET)
    public String addToGroup(Model model, Integer id) {
        return "create-group";
    }

    @RequestMapping(path = "/assign-to-group", method = RequestMethod.POST)
    public String assignToGroupPost(HttpSession session,Integer id,String name) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Guest guest = guests.findOne(id);
        Group group = groups.findFirstByName(name);
        guest.setInGroup(true);
        guest.setGroup(group);
        group.getGuestsInGroup().add(guest);
        guests.save(guest);
        groups.save(group);
        if (guest.getRoom().getNumber() == 0) {
            return "redirect:/unassigned-guests";
        }
        return "redirect:/guests";
    }

    @RequestMapping(path = "/assign-to-group", method = RequestMethod.GET)
    public String assignToGroup(Model model, Integer id) {
        Guest guest = guests.findOne(id);
        model.addAttribute("guest",guest);
        return "assign-to-group";
    }

    @RequestMapping(path = "/group-info",method = RequestMethod.GET)
    public String groupInfo(Model model, Integer id) {
        Group group = groups.findOne(id);
        Iterable<Guest> guestList = guests.findByGroup(group);
        model.addAttribute("group",group);
        model.addAttribute("guests",guestList);
        return "group-info";
    }
}
