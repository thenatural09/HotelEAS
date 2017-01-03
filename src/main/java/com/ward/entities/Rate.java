package com.ward.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

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
    double group;

    @Column
    double friendsAndFamily;

    @Column
    double aarp;

    @Column
    double employee;

    @Column
    double comp;

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

    public double getGroup() {
        return group;
    }

    public void setGroup(double group) {
        this.group = group;
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
