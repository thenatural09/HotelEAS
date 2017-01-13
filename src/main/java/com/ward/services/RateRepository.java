package com.ward.services;

import com.ward.entities.Rate;
import com.ward.entities.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Troy on 1/3/17.
 */
public interface RateRepository extends CrudRepository<Rate,Integer> {
    List<Rate> findByRoom(Room room);
    Rate findFirstByRoom(Room room);
    List<Rate> findByOrderByBaseDesc();
    List<Rate> findByOrderByBaseAsc();
}
