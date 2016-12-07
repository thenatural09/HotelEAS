package com.ward.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
    int numberOfRooms;

    @ManyToMany
    ArrayList<Room> rooms;

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

    @ManyToMany
    Guest guest;

    public Group() {
    }

    public Group(String name, int numberOfRooms, ArrayList<Room> rooms, double discount, String event, LocalDate arrival, LocalDate departure, User user, Guest guest) {
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.rooms = rooms;
        this.discount = discount;
        this.event = event;
        this.arrival = arrival;
        this.departure = departure;
        this.user = user;
        this.guest = guest;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
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

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
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