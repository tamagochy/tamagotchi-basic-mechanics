package ru.tamagotchi.basicmechanics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tamagotchi.basicmechanics.configuration.MappersConfiguration;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.domain.PetStatus;
import ru.tamagotchi.basicmechanics.dto.PetDto;

/**
 * Created by makar
 * 07.10.2018 10:25
 */
@Mapper(config = MappersConfiguration.class, imports = {PetStatus.class})
public interface PetMapper {

    @Mapping(expression = "java(pet.getStatus() == PetStatus.ACTIVE)", target = "active")
    PetDto toDto(Pet pet);
}
