package ru.tamagotchi.basicmechanics.dao;

import org.springframework.data.repository.CrudRepository;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.domain.PetStatus;

import java.util.Collection;
import java.util.List;

/**
 * Created by makar
 * 02.10.2018 18:15
 */

public interface PetDao extends CrudRepository<Pet, Long> {
    List<Pet> getAllByOwnerIdAndStatusIn(Integer ownerId, Collection<PetStatus> status);

    Long countAllByOwnerId(Integer ownerId);
}
