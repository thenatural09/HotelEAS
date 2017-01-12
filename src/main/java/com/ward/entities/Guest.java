package com.ward.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    int numberOfStays;

    @ManyToOne
    User user;

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
    Room room;

    @OneToMany
    List<CreditCard> creditCards;

    @Column
    boolean isAssigned;

    @Column
    Boolean hasCreditCard;

    @Column
    Boolean isInGroup;

    @ManyToOne
    Group group;

    @Column
    Double rate;

    @Column
    Boolean hasRate;

    @Column
    Boolean hasNotes;



    public Guest() {
    }

    public Guest(String firstName, String lastName, int numberOfGuests, String notes, String homeAddress, String phoneNumber, int numberOfStays, User user, LocalDate arrival, LocalDate departure, String email, LocalTime checkInTime, LocalTime checkOutTime, Room room, List<CreditCard> creditCards, boolean isAssigned, Boolean hasCreditCard, Boolean isInGroup, Group group,Boolean hasNotes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfGuests = numberOfGuests;
        this.notes = notes;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.numberOfStays = numberOfStays;
        this.user = user;
        this.arrival = arrival;
        this.departure = departure;
        this.email = email;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.room = room;
        this.creditCards = creditCards;
        this.isAssigned = isAssigned;
        this.hasCreditCard = hasCreditCard;
        this.isInGroup = isInGroup;
        this.group = group;
        this.hasNotes = hasNotes;
    }

    public Guest(String firstName, String lastName, int numberOfGuests, String notes, String homeAddress, String phoneNumber, int numberOfStays, User user, LocalDate arrival, LocalDate departure, String email, LocalTime checkInTime, LocalTime checkOutTime, Room room) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfGuests = numberOfGuests;
        this.notes = notes;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.numberOfStays = numberOfStays;
        this.user = user;
        this.arrival = arrival;
        this.departure = departure;
        this.email = email;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.room = room;
    }

    public Boolean getHasRate() {
        return hasRate;
    }

    public Boolean getHasNotes() {
        return hasNotes;
    }

    public void setHasNotes(Boolean hasNotes) {
        this.hasNotes = hasNotes;
    }

    public void setHasRate(Boolean hasRate) {
        this.hasRate = hasRate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Boolean getHasCreditCard() {
        return hasCreditCard;
    }

    public void setHasCreditCard(Boolean hasCreditCard) {
        this.hasCreditCard = hasCreditCard;
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setInGroup(Boolean inGroup) {
        isInGroup = inGroup;
    }

    public Boolean getInGroup() {
        return isInGroup;
    }

    public boolean isHasCreditCard() {
        return hasCreditCard;
    }

    public void setHasCreditCard(boolean hasCreditCard) {
        this.hasCreditCard = hasCreditCard;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

}
