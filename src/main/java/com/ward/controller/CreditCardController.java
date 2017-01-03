package com.ward.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ward.entities.CreditCard;
import com.ward.entities.Guest;
import com.ward.entities.User;
import com.ward.services.*;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmIndexManyToAnyType;
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
public class CreditCardController {
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

    @RequestMapping(path = "/create-credit-card", method = RequestMethod.POST)
    public String createCreditCard(Integer id, HttpSession session, String type, String ownerName, Long number, String expirationDate, String billingAddress) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        ArrayList<CreditCard> creditCardArrayList = new ArrayList<>();
        Guest g = guests.findOne(id);
        CreditCard creditCard = new CreditCard(type,ownerName,number, LocalDate.parse(expirationDate),billingAddress,user);
        creditCard.setGuest(g);
        creditCardArrayList.add(creditCard);
        g.setCreditCards(creditCardArrayList);
        g.setHasCreditCard(true);
        creditCards.save(creditCard);
        guests.save(g);
        if (g.getRoom().getNumber() == 0) {
            return "redirect:/unassigned-guests";
        }
        return "redirect:/guests";
    }

    @RequestMapping(path ="/create-credit-card",method = RequestMethod.GET)
    public String getCreditCard(Integer id,Model model) {
        Guest guest = guests.findOne(id);
        model.addAttribute("guest",guest);
        return "create-credit-card";
    }

    @RequestMapping(path = "/credit-card-info",method = RequestMethod.GET)
    public String creditCardInfo(Model model, Integer id) {
        Guest guest = guests.findOne(id);
        Iterable<CreditCard> creditCardList = creditCards.findByGuest(guest);
        model.addAttribute("creditCards",creditCardList);
        return "credit-card-info";
    }

    @RequestMapping(path = "/edit-credit-card-info", method = RequestMethod.POST)
    public String editCardPost(HttpSession session,Integer id,String type, String ownerName, Long number, String expirationDate, String billingAddress) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("Forbidden");
        }
        CreditCard creditCard = creditCards.findOne(id);
        creditCard.setType(type);
        creditCard.setOwnerName(ownerName);
        creditCard.setNumber(number);
        creditCard.setExpirationDate(LocalDate.parse(expirationDate));
        creditCard.setBillingAddress(billingAddress);
        creditCards.save(creditCard);
        return "redirect:/guests";
    }

    @RequestMapping(path = "/edit-credit-card-info", method = RequestMethod.GET)
    public String editCardGet(Model model,Integer id) {
        CreditCard creditCard = creditCards.findOne(id);
        model.addAttribute("creditCard",creditCard);
        return "edit-credit-card-info";
    }
}
