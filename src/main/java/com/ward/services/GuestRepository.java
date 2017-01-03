package com.ward.services;

import com.ward.entities.Group;
import com.ward.entities.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Troy on 12/6/16.
 */
public interface GuestRepository extends CrudRepository<Guest,Integer> {
    Guest findFirstByFirstNameAndLastName(String firstName,String lastName);
    List<Guest> findByGroup(Group group);
    List<Guest> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String firstName,String lastName, String email);
}
