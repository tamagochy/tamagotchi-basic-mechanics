package ru.tamagotchi.basicmechanics.service.api;

import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.service.ActionRequest;

/**
 * Created by makar
 * 02.10.2018 18:26
 */
public interface PetService {
    Pet getCurrent();
    Pet create(String name);
    Pet feed(ActionRequest actionRequest);
    Pet sleep();
    Pet treat(ActionRequest actionRequest);
    Pet play(ActionRequest actionRequest);
}
