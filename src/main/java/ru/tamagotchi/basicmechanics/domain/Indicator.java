package ru.tamagotchi.basicmechanics.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by makar
 * 17.12.2018 21:02
 */
@Entity
@Table(name = "INDICATORS")
@Data
public class Indicator {
    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private IndicatorCode code;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private RoomCode roomCode;

    @Column(length = 100)
    private String description;

    public boolean accepted(IndicatorCode indicator, RoomCode room) {
        return code == indicator && room == roomCode;
    }
}
