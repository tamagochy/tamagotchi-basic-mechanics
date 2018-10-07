package ru.tamagotchi.basicmechanics.mapper;

import org.mapstruct.Mapper;
import ru.tamagotchi.basicmechanics.configuration.MappersConfiguration;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.dto.PetDto;

/**
 * Created by makar
 * 07.10.2018 10:25
 */
@Mapper(config = MappersConfiguration.class)
public interface PetMapper {
    PetDto toDto(Pet pet);
}
