package ru.tamagotchi.basicmechanics.service.api;

import ru.tamagotchi.basicmechanics.domain.Action;

import java.util.List;

/**
 * Created by makar
 * 09.10.2018 18:38
 */
public interface ActionService {
    List<Action> getActions();
}
