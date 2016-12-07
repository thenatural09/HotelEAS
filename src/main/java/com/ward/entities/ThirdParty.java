package com.ward.entities;

import javax.persistence.*;

/**
 * Created by Troy on 12/6/16.
 */
@Entity
@Table(name = "third_parties")
public class ThirdParty {
    @Id
    @GeneratedValue
    int id;

    @Column
    String name;

    @Column
    boolean prePaid;

    @Column
    double rate;

    public ThirdParty() {
    }

    public ThirdParty(String name, boolean prePaid, double rate) {
        this.name = name;
        this.prePaid = prePaid;
        this.rate = rate;
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

    public boolean isPrePaid() {
        return prePaid;
    }

    public void setPrePaid(boolean prePaid) {
        this.prePaid = prePaid;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
