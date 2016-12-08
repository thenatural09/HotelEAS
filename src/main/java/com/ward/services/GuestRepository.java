package com.ward.services;

import com.ward.entities.Guest;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Troy on 12/6/16.
 */
public interface GuestRepository extends CrudRepository<Guest,Integer> {
    Guest findFirstByFirstNameAndLastName(String firstName,String lastName);
}
