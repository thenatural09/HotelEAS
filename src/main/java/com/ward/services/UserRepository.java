package com.ward.services;

import com.ward.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Troy on 12/6/16.
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    User findFirstByUsername(String username);
}
