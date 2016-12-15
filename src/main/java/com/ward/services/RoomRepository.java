package com.ward.services;

import com.ward.entities.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by Troy on 12/6/16.
 */
public interface RoomRepository extends CrudRepository<Room,Integer> {
    Room findFirstByNumber(int number);
    List<Room> findByOrderByNumberDesc();
}
