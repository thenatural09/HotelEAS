package com.ward.services;

import com.ward.entities.ThirdParty;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Troy on 12/6/16.
 */
public interface ThirdPartyRepository extends CrudRepository<ThirdParty,Integer> {
}
