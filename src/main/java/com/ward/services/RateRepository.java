package com.ward.services;

import com.ward.entities.Rate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Troy on 1/3/17.
 */
public interface RateRepository extends CrudRepository<Rate,Integer> {
}
