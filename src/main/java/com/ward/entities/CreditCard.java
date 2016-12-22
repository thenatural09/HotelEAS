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
    Long number;

    @Column
    LocalDate expirationDate;

    @Column
    String billingAddress;

    @ManyToOne
    User user;

    public CreditCard() {
    }

    public CreditCard(String type, Long number, LocalDate expirationDate, String billingAddress, User user) {
        this.type = type;
        this.number = number;
        this.expirationDate = expirationDate;
        this.billingAddress = billingAddress;
        this.user = user;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
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

}
