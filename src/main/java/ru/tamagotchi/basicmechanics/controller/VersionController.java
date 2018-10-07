package ru.tamagotchi.basicmechanics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tamagotchi.basicmechanics.configuration.VersionProperties;
import ru.tamagotchi.basicmechanics.dto.VersionDto;

/**
 * Created by makar
 * 07.10.2018 10:59
 */
@RestController
@RequestMapping(value = "/version")
@RequiredArgsConstructor
public class VersionController {

    private final VersionProperties versionProperties;

    @GetMapping
    public VersionDto version() {
        return new VersionDto(versionProperties.format());
    }
}
