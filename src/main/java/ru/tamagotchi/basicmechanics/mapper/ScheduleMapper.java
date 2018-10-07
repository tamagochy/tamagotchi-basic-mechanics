package ru.tamagotchi.basicmechanics.mapper;

import org.mapstruct.Mapper;
import ru.tamagotchi.basicmechanics.configuration.MappersConfiguration;
import ru.tamagotchi.basicmechanics.domain.ScheduleItem;
import ru.tamagotchi.basicmechanics.dto.ScheduleItemDto;

import java.util.List;

/**
 * Created by makar
 * 07.10.2018 18:31
 */
@Mapper(config = MappersConfiguration.class)
public interface ScheduleMapper {
    ScheduleItemDto toDto(ScheduleItem item);

    List<ScheduleItemDto> toDtos(List<ScheduleItem> items);
}
