package com.ward.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Troy on 12/6/16.
 */
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    int number;

    @Column(nullable = false)
    int numberOfBeds;

    @Column
    String type;

    @ManyToOne
    User user;

    @Column
    Boolean hasGuest;

    @Column
    boolean isClean;

    @OneToMany
    List<Rate> rateList;

    @Column
    Boolean hasRates;


    public Room() {
    }

    public Room(int number, int numberOfBeds, String type, User user, Boolean hasGuest, boolean isClean) {
        this.number = number;
        this.numberOfBeds = numberOfBeds;
        this.type = type;
        this.user = user;
        this.hasGuest = hasGuest;
        this.isClean = isClean;
    }

    public Room(int number, int numberOfBeds, String type, User user) {
        this.number = number;
        this.numberOfBeds = numberOfBeds;
        this.type = type;
        this.user = user;
    }

    public Boolean getHasRates() {
        return hasRates;
    }

    public void setHasRates(Boolean hasRates) {
        this.hasRates = hasRates;
    }

    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public boolean isHasGuest() {
        return hasGuest;
    }

    public void setHasGuest(boolean hasGuest) {
        this.hasGuest = hasGuest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
