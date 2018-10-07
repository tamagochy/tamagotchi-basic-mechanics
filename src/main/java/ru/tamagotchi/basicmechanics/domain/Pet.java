package ru.tamagotchi.basicmechanics.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by makar
 * 02.10.2018 18:13
 */
@Entity
@Table(name = "PETS")
@Data
public class Pet {
    public static final int INDICATOR_MAX_VALUE = 100;
    public static final int INDICATOR_INCREMENT_VALUE = 25;
    public static final int INDICATOR_CRITICAL_VALUE = 25;

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
    private LocalDateTime createTime;

    @Column(nullable = false)
    private LocalDateTime lastAccessTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;

    public Pet(
            String name,
            Integer ownerId,
            Integer health,
            Integer hunger,
            Integer rest,
            Integer mood,
            LocalDateTime createTime,
            LocalDateTime lastAccessTime,
            PetStatus status
    ) {
        this.name = name;
        this.ownerId = ownerId;
        this.health = health;
        this.hunger = hunger;
        this.rest = rest;
        this.mood = mood;
        this.createTime = createTime;
        this.lastAccessTime = lastAccessTime;
        this.status = status;
    }

    public void increaseHealth() {
        this.health = INDICATOR_MAX_VALUE;
    }

    public void decreaseHealth() {
        this.health = INDICATOR_CRITICAL_VALUE;
    }

    public void increaseHunger() {
        this.hunger += INDICATOR_INCREMENT_VALUE;
        if (this.hunger > INDICATOR_MAX_VALUE) {
            this.hunger = INDICATOR_MAX_VALUE;
        }
    }

    public void decreaseHunger() {
        this.hunger = INDICATOR_CRITICAL_VALUE;
    }

    public void increaseRest() {
        this.rest = INDICATOR_MAX_VALUE;
    }

    public void decreaseRest() {
        this.rest = INDICATOR_CRITICAL_VALUE;
    }

    public void increaseMood(Integer value) {
        this.mood += value;
        if (this.mood > INDICATOR_MAX_VALUE) {
            this.mood = INDICATOR_MAX_VALUE;
        }
    }

    public void decreaseMood() {
        this.mood = INDICATOR_CRITICAL_VALUE;
    }

    public boolean hasCriticalIndicator() {
        return health <= INDICATOR_CRITICAL_VALUE ||
                hunger <= INDICATOR_CRITICAL_VALUE ||
                mood <= INDICATOR_CRITICAL_VALUE ||
                rest <= INDICATOR_CRITICAL_VALUE;
    }
}
