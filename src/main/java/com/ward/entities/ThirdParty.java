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
    boolean hasPrePay;

    @Column
    double rate;

    public ThirdParty() {
    }

    public ThirdParty(String name, boolean hasPrePay, double rate) {
        this.name = name;
        this.hasPrePay = hasPrePay;
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

    public boolean isHasPrePay() {
        return hasPrePay;
    }

    public void setHasPrePay(boolean hasPrePay) {
        this.hasPrePay = hasPrePay;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
