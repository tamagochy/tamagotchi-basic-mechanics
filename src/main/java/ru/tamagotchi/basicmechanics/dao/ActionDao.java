package ru.tamagotchi.basicmechanics.dao;

import org.springframework.data.repository.CrudRepository;
import ru.tamagotchi.basicmechanics.domain.Action;
import ru.tamagotchi.basicmechanics.domain.ActionCode;

/**
 * Created by makar
 * 07.10.2018 21:19
 */
public interface ActionDao extends CrudRepository<Action, Long> {
    Action getByCode(ActionCode code);
}
