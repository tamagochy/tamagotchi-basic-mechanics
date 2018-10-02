package ru.tamagotchi.basicmechanics.service.api;

import ru.tamagotchi.basicmechanics.domain.Pet;

/**
 * Created by makar
 * 02.10.2018 18:26
 */
public interface PetService {
    Pet getCurrentUserPet();

    Pet createNew();
}
