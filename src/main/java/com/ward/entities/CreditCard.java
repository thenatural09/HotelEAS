package com.ward.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Troy on 12/6/16.
 */
@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue
    int id;

    @Column
    String type;

    @Column
    @JsonIgnore
    int number;

    @Column
    LocalDate expirationDate;

    @Column
    String billingAddress;

    @ManyToMany
    Guest guest;

    public CreditCard() {
    }

    public CreditCard(String type, int number, LocalDate expirationDate, String billingAddress, Guest guest) {
        this.type = type;
        this.number = number;
        this.expirationDate = expirationDate;
        this.billingAddress = billingAddress;
        this.guest = guest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
