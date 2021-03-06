package ru.tamagotchi.basicmechanics.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

/**
 * Created by makar
 * 02.10.2018 18:13
 */
@Entity
@Table(name = "PETS")
@Data
public class Pet {
    public static final int INDICATOR_MAX_VALUE = 100;
    public static final int INDICATOR_CRITICAL_VALUE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer ownerId;

    @Column(nullable = false)
    private Integer health;

    @Column(nullable = false)
    private Integer hunger;

    @Column(nullable = false)
    private Integer rest;

    @Column(nullable = false)
    private Integer mood;

    @Column(nullable = false)
    private LocalDateTime lastHealthIncreaseTime;

    @Column(nullable = false)
    private LocalDateTime lastHungerIncreaseTime;

    @Column(nullable = false)
    private LocalDateTime lastRestIncreaseTime;

    @Column(nullable = false)
    private LocalDateTime lastMoodIncreaseTime;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column(nullable = false)
    private LocalDateTime lastScheduleApplyTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;

    @Column(name = "DISEASE_CODE", length = 20)
    @Enumerated(EnumType.STRING)
    private DiseaseCode diseaseCode;

    @Transient
    private Integer score;

    public Pet(String name, Integer ownerId, LocalDateTime createTime) {
        this.name = name;
        this.ownerId = ownerId;
        this.health = INDICATOR_MAX_VALUE;
        this.hunger = INDICATOR_MAX_VALUE;
        this.rest = INDICATOR_MAX_VALUE;
        this.mood = INDICATOR_MAX_VALUE;
        this.createTime = createTime;
        this.lastHealthIncreaseTime = createTime;
        this.lastHungerIncreaseTime = createTime;
        this.lastRestIncreaseTime = createTime;
        this.lastMoodIncreaseTime = createTime;
        this.lastScheduleApplyTime = createTime;
        this.status = PetStatus.ACTIVE;
    }

    public void increaseHealth(int value) {
        health += value;
        if (health > INDICATOR_MAX_VALUE) {
            health = INDICATOR_MAX_VALUE;
        }
        lastHealthIncreaseTime = now();
    }

    public void decreaseHealth(int value) {
        health -= value;
        if (health < INDICATOR_CRITICAL_VALUE) {
            health = INDICATOR_CRITICAL_VALUE;
        }
    }

    public void increaseHunger(int value) {
        hunger += value;
        if (hunger > INDICATOR_MAX_VALUE) {
            hunger = INDICATOR_MAX_VALUE;
        }
        lastHungerIncreaseTime = now();
    }

    public void decreaseHunger() {
        hunger = INDICATOR_CRITICAL_VALUE;
    }

    public void increaseRest() {
        rest = INDICATOR_MAX_VALUE;
        lastRestIncreaseTime = now();
    }

    public void decreaseRest() {
        rest = INDICATOR_CRITICAL_VALUE;
    }

    public void increaseMood(Integer value) {
        mood += value;
        if (mood > INDICATOR_MAX_VALUE) {
            mood = INDICATOR_MAX_VALUE;
        }
        lastMoodIncreaseTime = now();
    }

    public void decreaseMood() {
        mood = INDICATOR_CRITICAL_VALUE;
    }

    public void decreaseIndicator(Indicator indicator, int value) {
        if (indicator.getCode() == IndicatorCode.health) {
            decreaseHealth(value);
        }
    }

    public void leave() {
        status = PetStatus.LEAVE;
    }
}
