package ru.tamagotchi.basicmechanics.service;

import lombok.Getter;
import ru.tamagotchi.basicmechanics.domain.ActionCode;
import ru.tamagotchi.basicmechanics.domain.RoomCode;
import ru.tamagotchi.basicmechanics.dto.ActionRequestDto;

/**
 * Created by makar
 * 18.12.2018 0:36
 */
@Getter
public class ActionRequest {
    private final ActionCode actionCode;
    private final RoomCode roomCode;

    public ActionRequest(ActionRequestDto dto) {
        this.actionCode = ActionCode.valueOf(dto.getAction());
        this.roomCode = RoomCode.valueOf(dto.getRoom());
    }
}
