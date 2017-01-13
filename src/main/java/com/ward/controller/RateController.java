package com.ward.controller;

import com.ward.entities.Rate;
import com.ward.entities.Room;
import com.ward.entities.User;
import com.ward.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Troy on 1/11/17.
 */
@Controller
public class RateController {
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

    @RequestMapping(path = "/create-rates", method = RequestMethod.POST)
    public String postRates(HttpSession session, Integer id, Double base, Double friendsAndFamily, Double aarp, Double employee, Double comp) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        ArrayList<Rate> rateArrayList = new ArrayList<>();
        Room room = rooms.findOne(id);
        Rate rate = new Rate(base,friendsAndFamily,aarp,employee,comp);
        rateArrayList.add(rate);
        rate.setRoom(room);
        room.setRateList(rateArrayList);
        room.setHasRates(true);
        rates.save(rate);
        rooms.save(room);
        return "redirect:/";
    }

    @RequestMapping(path = "/create-rates", method = RequestMethod.GET)
    public String getCreateRates(Model model, Integer id) {
        Room room = rooms.findOne(id);
        model.addAttribute("room",room);
        return "create-rates";
    }

    @RequestMapping(path = "/rate-info",method = RequestMethod.GET)
    public String getRateInfo(Model model,Integer id) {
        Room room = rooms.findOne(id);
        Iterable<Rate> rateList = rates.findByRoom(room);
        model.addAttribute("room",room);
        model.addAttribute("rates",rateList);
        return "rate-info";
    }

    @RequestMapping(path = "/rates", method = RequestMethod.GET)
    public String rates(Model model,Integer id,HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Iterable<Rate> rateList = rates.findAll();
        model.addAttribute("rates",rateList);
        return "rates";
    }

    @RequestMapping(path = "/descending-rates", method = RequestMethod.GET)
    public String descRates(Model model,HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Iterable<Rate> rateList = rates.findByOrderByBaseDesc();
        model.addAttribute("rates",rateList);
        return "descending-rates";
    }

    @RequestMapping(path = "/ascending-rates", method = RequestMethod.GET)
    public String ascRates(Model model,HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        Iterable<Rate> rateList = rates.findByOrderByBaseAsc();
        model.addAttribute("rates",rateList);
        return "ascending-rates";
    }
}
