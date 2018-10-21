package ru.tamagotchi.basicmechanics.dao;

import org.springframework.data.repository.CrudRepository;
import ru.tamagotchi.basicmechanics.domain.ScheduleItem;

import java.util.List;

/**
 * Created by makar
 * 07.10.2018 13:51
 */
public interface ScheduleItemDao extends CrudRepository<ScheduleItem, Long> {
    @Override
    List<ScheduleItem> findAll();
}
