package com.ward.entities;

import javax.persistence.*;

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
    String address;

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
}
