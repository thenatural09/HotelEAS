package com.ward.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Troy on 12/6/16.
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    int id;

    @Column
    String name;

    @Column
    double discount;

    @Column
    String event;

    @Column
    LocalDate arrival;

    @Column
    LocalDate departure;

    @ManyToOne
    User user;

    @OneToMany
    List<Guest> guestsInGroup;


    public Group() {
    }

    public Group(String name, double discount, String event, LocalDate arrival, LocalDate departure, User user) {
        this.name = name;
        this.discount = discount;
        this.event = event;
        this.arrival = arrival;
        this.departure = departure;
        this.user = user;
    }

    public List<Guest> getGuestsInGroup() {
        return guestsInGroup;
    }

    public void setGuestsInGroup(ArrayList<Guest> guestsInGroup) {
        this.guestsInGroup = guestsInGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
