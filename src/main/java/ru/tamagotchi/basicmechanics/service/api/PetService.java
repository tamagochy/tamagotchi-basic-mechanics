package ru.tamagotchi.basicmechanics.service.api;

import ru.tamagotchi.basicmechanics.domain.Pet;

/**
 * Created by makar
 * 02.10.2018 18:26
 */
public interface PetService {
    Pet getCurrent();

    Pet create();

    Pet feed();

    Pet sleep();

    Pet treat();

    Pet play();
}
