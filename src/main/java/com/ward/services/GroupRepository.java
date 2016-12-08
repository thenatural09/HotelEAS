package com.ward.services;

import com.ward.entities.Group;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Troy on 12/6/16.
 */
public interface GroupRepository extends CrudRepository<Group,Integer> {
    Group findFirstByName(String name);
}
