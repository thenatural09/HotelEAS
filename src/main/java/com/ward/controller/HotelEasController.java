package com.ward.controller;

import com.ward.entities.User;
import com.ward.services.*;
import com.ward.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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
    public String home(Model model, HttpSession session) {
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
}
