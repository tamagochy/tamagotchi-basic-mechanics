package ru.tamagotchi.basicmechanics.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * Created by makar
 * 07.10.2018 12:45
 */
@Entity
@Table(name = "SCHEDULE_ITEMS")
@Data
@EqualsAndHashCode(of = "time")
public class ScheduleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false, length = 50)
    private String description;

    @Column(name = "IS_BEGINNING_OF_DAY", nullable = false)
    private boolean beginningOfDay;

    @Column(name = "IS_DECREASE_HUNGER", nullable = false)
    private boolean decreaseHunger;

    @Column(name = "IS_DECREASE_REST", nullable = false)
    private boolean decreaseRest;

    @Column(name = "IS_DECREASE_MOOD", nullable = false)
    private boolean decreaseMood;
}
