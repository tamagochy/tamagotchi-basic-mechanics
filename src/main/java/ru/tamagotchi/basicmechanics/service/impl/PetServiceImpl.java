package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.PetDao;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.domain.PetStatus;
import ru.tamagotchi.basicmechanics.exception.PetNotFound;
import ru.tamagotchi.basicmechanics.service.api.PetService;

import java.time.LocalDateTime;

/**
 * Created by makar
 * 02.10.2018 18:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetDao petDao;

    @Override
    public Pet getCurrentUserPet() {
        return petDao.getAllByOwnerId(42)
                .stream()
                .filter(pet -> pet.getStatus().equals(PetStatus.ACTIVE))
                .findFirst()
                .orElseThrow(PetNotFound::new);
    }

    @Override
    public Pet createNew() {
        Pet pet = new Pet(42, 100, 100, 100, 100, LocalDateTime.now(), PetStatus.ACTIVE);
        return petDao.save(pet);
    }
}
