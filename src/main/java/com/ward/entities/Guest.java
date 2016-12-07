package com.ward.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Troy on 12/6/16.
 */
@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue
    int id;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    int numberOfGuests;

    @Column
    String notes;

    @Column
    String homeAddress;

    @Column
    String phoneNumber;

    @Column
    int reservationNumber;

    @Column
    int numberOfStays;

    @ManyToOne
    User user;

    @ManyToOne
    Room room;

    @Column
    LocalDate arrival;

    @Column
    LocalDate departure;

    @Column
    String email;

    @Column
    LocalTime checkInTime;

    @Column
    LocalTime checkOutTime;

    @ManyToOne
    CreditCard creditCard;

    @ManyToOne
    Group group;

    @ManyToOne
    ThirdParty thirdParty;

    public Guest() {
    }

    public Guest(String firstName, String lastName, int numberOfGuests, String notes, String homeAddress, String phoneNumber, int reservationNumber, int numberOfStays, User user, Room room, LocalDate arrival, LocalDate departure, String email, LocalTime checkInTime, LocalTime checkOutTime, CreditCard creditCard, Group group, ThirdParty thirdParty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfGuests = numberOfGuests;
        this.notes = notes;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.reservationNumber = reservationNumber;
        this.numberOfStays = numberOfStays;
        this.user = user;
        this.room = room;
        this.arrival = arrival;
        this.departure = departure;
        this.email = email;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.creditCard = creditCard;
        this.group = group;
        this.thirdParty = thirdParty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getNumberOfStays() {
        return numberOfStays;
    }

    public void setNumberOfStays(int numberOfStays) {
        this.numberOfStays = numberOfStays;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ThirdParty getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(ThirdParty thirdParty) {
        this.thirdParty = thirdParty;
    }
}
