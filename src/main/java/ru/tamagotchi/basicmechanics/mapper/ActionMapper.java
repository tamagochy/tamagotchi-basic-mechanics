package ru.tamagotchi.basicmechanics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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

    @Mappings({
            @Mapping(source = "mainIndicator.roomCode", target = "room")
    })
    ActionDto toDto(Action action);

    List<ActionDto> toDtoList(List<Action> actions);
}
