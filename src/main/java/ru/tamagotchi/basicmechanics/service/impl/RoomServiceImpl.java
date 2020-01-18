package ru.tamagotchi.basicmechanics.service.impl;

import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.domain.RoomCode;
import ru.tamagotchi.basicmechanics.service.api.RoomService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by makar
 * 17.12.2018 23:57
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Override
    public List<String> roomCodes() {
        return Arrays.stream(RoomCode.values()).map(Enum::name).collect(Collectors.toList());
    }
}
