package ru.tamagotchi.basicmechanics.mapper;

import org.mapstruct.Mapper;
import ru.tamagotchi.basicmechanics.configuration.MappersConfiguration;
import ru.tamagotchi.basicmechanics.domain.Action;
import ru.tamagotchi.basicmechanics.dto.ActionDto;

import java.util.List;

/**
 * Created by makar
 * 09.10.2018 18:37
 */
@Mapper(config = MappersConfiguration.class)
public interface ActionMapper {
    ActionDto toDto(Action action);

    List<ActionDto> toDtos(List<Action> actions);
}
