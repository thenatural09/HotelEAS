package com.ward.services;

import com.ward.entities.CreditCard;
import com.ward.entities.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Troy on 12/6/16.
 */
public interface CreditCardRepository extends CrudRepository<CreditCard,Integer> {
    CreditCard findFirstByType(String type);
    List<CreditCard> findByGuest(Guest guest);
}
