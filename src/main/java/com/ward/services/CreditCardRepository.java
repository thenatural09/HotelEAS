package com.ward.services;

import com.ward.entities.CreditCard;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Troy on 12/6/16.
 */
public interface CreditCardRepository extends CrudRepository<CreditCard,Integer> {
}
