package com.ward.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Troy on 1/3/17.
 */
@Entity
@Table(name = "rates")
public class Rate {
    @Id
    @GeneratedValue
    int id;

    @Column
    double base;

    @Column
    double friendsAndFamily;

    @Column
    double aarp;

    @Column
    double employee;

    @Column
    double comp;

    @ManyToOne
    Room room;

    @OneToMany
    List<Group> groupList;

    @OneToMany
    List<ThirdParty> thirdPartyList;

    public Rate(double base, double friendsAndFamily, double aarp, double employee, double comp) {
        this.base = base;
        this.friendsAndFamily = friendsAndFamily;
        this.aarp = aarp;
        this.employee = employee;
        this.comp = comp;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<ThirdParty> getThirdPartyList() {
        return thirdPartyList;
    }

    public void setThirdPartyList(List<ThirdParty> thirdPartyList) {
        this.thirdPartyList = thirdPartyList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getFriendsAndFamily() {
        return friendsAndFamily;
    }

    public void setFriendsAndFamily(double friendsAndFamily) {
        this.friendsAndFamily = friendsAndFamily;
    }

    public double getAarp() {
        return aarp;
    }

    public void setAarp(double aarp) {
        this.aarp = aarp;
    }

    public double getEmployee() {
        return employee;
    }

    public void setEmployee(double employee) {
        this.employee = employee;
    }

    public double getComp() {
        return comp;
    }

    public void setComp(double comp) {
        this.comp = comp;
    }
}
